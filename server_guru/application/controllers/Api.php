<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Api extends CI_Controller {


	function packet_private(){

		$result = $this->db->get('tb_jenis_private');

		if ($result -> num_rows() > 0 ){

			$data['pesan'] = 'data ada' ;
			$data['status'] = true ;
			$data['data'] = $result ->result();
		}
		else {
			$data['pesan'] = 'data tidak ada';
			$data['status'] = false ;

		}
		echo json_encode($data);


	}



	function insert_tracking(){

		$iduser = $this->input->post('iduser');
		$lat = $this->input->post('lat');
		$lon = $this->input->post('lon');


		$this->db->where('trancking_user',$iduser);

		$result = $this->db->get('tb_tracking');


		if ($result -> num_rows() > 0 ){


			$this->db->where('trancking_user',$iduser);

			$simpan['tracking_lat'] = $lat ;
			$simpan['tracking_lon'] = $lon ;
			$hasil = $this->db->update('tb_tracking',$simpan);

			if($hasil){

			$data['pesan'] = 'data ada' ;
			$data['status'] = true ;
		}
		else {
			$data['pesan'] = 'data tidak ada';
			$data['status'] = false ;

		}
		echo json_encode($data);

			


		}else {

				$simpan['tracking_lat'] = $lat ;
			$simpan['tracking_lon'] = $lon ;
			$simpan['trancking_user'] = $iduser;
			$hasil = $this->db->insert('tb_tracking',$simpan);

			if($hasil){

			$data['pesan'] = 'berhasil' ;
			$data['status'] = true ;
		}
		else {
			$data['pesan'] = 'tidak berhasil';
			$data['status'] = false ;

		}
		echo json_encode($data);


		}
	}


function list_request_guru(){

	$id = $this->input->post('id');

	$this->db->where('order_guru',$id);

	$result = $this->db->get('tb_booking');

	if ($result -> num_rows() > 0 ){

			$data['pesan'] = 'data ada' ;
			$data['status'] = true ;
			$data['data'] = $result ->result();
		}
		else {
			$data['pesan'] = 'data tidak ada';
			$data['status'] = false ;

		}
		echo json_encode($data);


}


	function login_guru (){

			$name = $this->input->post('email');
			$password = $this->input->post('password');

			$this->db->where('user_email',$name);
			$this->db->where('user_password',$password);
			$this->db->where('user_level',2);

			$result = $this->db->get('tb_user');
			if ($result -> num_rows() > 0 ){

			$data['pesan'] = 'login berhasil' ;
			$data['status'] = true ;
			$data['data'] = $result ->row();
		}
		else {
			$data['pesan'] = 'login tidak berhasil';
			$data['status'] = false ;

		}
		echo json_encode($data);




	}
public function upload_transfer2($folder = 'data', $size = 3000000)
	{
		$data = array();
	    $folder = 'img/'.$folder.'/';

	   	$filename = $_FILES["userfile2"]["name"];

	   //	echo $filename ;
		$file_basename = substr($filename, 0, strripos($filename, '.')); // get file extention
		$file_ext = substr($filename, strripos($filename, '.')); // get file name
		$filesize = $_FILES["userfile"]["size"];
		$allowed_file_types = array('.jpg','.png');	

		if (in_array($file_ext,$allowed_file_types) && ($filesize < $size))
		{	
			// Rename file
			//buatDir($folder);
			$newfilename = md5($file_basename.date('YmdHis')) . $file_ext;
			if (file_exists($folder . $newfilename))
			{
				// file already exists error
				$data['result'] = "false";
				$data['msg'] = "File / nama file sudah ada diserver";
			} else	{		
				if(move_uploaded_file($_FILES["userfile2"]["tmp_name"], $folder . $newfilename)){
					$data['result'] = "true";
					$data['namafile'] = $newfilename;
					$data['msg'] = "Upload file berhasil.";
				}else{
					$data['result'] = "false";
					$data['msg'] = "Upload File Gagal, Silahkan coba lagi";
				}
				
			}
		}elseif (empty($file_basename)){	
			$data['result'] = "false";
			$data['msg'] = "Silahkan Pilih File untuk diupload, Silahkan coba lagi";
		}elseif ($filesize > $size){	
			$data['result'] = "false";
			$data['msg'] = "Ukuran file Terlalu besar max 1MB, Silahkan coba lagi";
		}else{
			// file type error
			unlink($_FILES["userfile"]["tmp_name"]);

			$data['result'] = "false";
			$data['msg'] = "File yang diupload harus berektensi ".implode(', ',$allowed_file_types);
		}

		return $data;
	}

	public function upload_transfer($folder = 'data', $size = 3000000)
	{
		$data = array();
	    $folder = 'img/'.$folder.'/';

	   	$filename = $_FILES["userfile"]["name"];

	  // 	echo $filename ;
		$file_basename = substr($filename, 0, strripos($filename, '.')); // get file extention
		$file_ext = substr($filename, strripos($filename, '.')); // get file name
		$filesize = $_FILES["userfile"]["size"];
		$allowed_file_types = array('.jpg','.png');	

		if (in_array($file_ext,$allowed_file_types) && ($filesize < $size))
		{	
			// Rename file
			//buatDir($folder);
			$newfilename = md5($file_basename.date('YmdHis')) . $file_ext;
			if (file_exists($folder . $newfilename))
			{
				// file already exists error
				$data['result'] = "false";
				$data['msg'] = "File / nama file sudah ada diserver";
			} else	{		
				if(move_uploaded_file($_FILES["userfile"]["tmp_name"], $folder . $newfilename)){
					$data['result'] = "true";
					$data['namafile'] = $newfilename;
					$data['msg'] = "Upload file berhasil.";
				}else{
					$data['result'] = "false";
					$data['msg'] = "Upload File Gagal, Silahkan coba lagi";
				}
				
			}
		}elseif (empty($file_basename)){	
			$data['result'] = "false";
			$data['msg'] = "Silahkan Pilih File untuk diupload, Silahkan coba lagi";
		}elseif ($filesize > $size){	
			$data['result'] = "false";
			$data['msg'] = "Ukuran file Terlalu besar max 1MB, Silahkan coba lagi";
		}else{
			// file type error
			unlink($_FILES["userfile"]["tmp_name"]);

			$data['result'] = "false";
			$data['msg'] = "File yang diupload harus berektensi ".implode(', ',$allowed_file_types);
		}

		return $data;
	}

	function register_guru(){

		$name = $this->input->post('name');
		$email = $this->input->post('email');
		$password = $this->input->post('password');
		$alamat = $this->input->post('alamat');
		$kelurahan = $this->input->post('kelurahan');
		$kec = $this->input->post('kec');
		$kab = $this->input->post('kab');
		$telpon = $this->input->post('telpon');
		$jp = $this->input->post('jp');

		$namafile = "";
		$namafile2 = "";

		if(!empty($_FILES['userfile'])){
			$hasil = $this->upload_transfer('data');

			if($hasil['result'] == 'false'){
				$data['result'] = 'false';
				$data['msg'] = $hasil['msg'];

				echo json_encode($data);
				return;
			}else{
				$namafile = $hasil['namafile'];


		if(!empty($_FILES['userfile2'])){
			$hasil = $this->upload_transfer2('data');

			if($hasil['result'] == 'false'){
				$data['result'] = 'false';
				$data['msg'] = $hasil['msg'];

				echo json_encode($data);
				return;
			}else{
				$namafile2 = $hasil['namafile'];


				//$namafile =$hasil2['namafile2'];
				
			}
		}


				//$namafile =$hasil2['namafile2'];
				
			}
		}

		$masuk['user_nama'] = $name ;
		$masuk['user_email'] = $email ;
		$masuk['user_alamat'] = $alamat ;
		$masuk['user_password'] = $password ;
		$masuk['user_kelurahan'] = $kelurahan ;
		$masuk['user_kec'] = $kec ;
		$masuk['user_kab'] = $kab ;
		$masuk['user_telpon'] = $telpon ;
		$masuk['user_ktp'] = $namafile;
		$masuk['user_ijazah'] = $namafile2;
		$masuk['user_level'] = "2" ;
		$masuk['user_jp'] = $jp ;
		$status = $this->db->insert('tb_user',$masuk);

		if($status){
			$data['pesan'] = 'register berhasil' ;
			$data['status'] = true ; 
		}
		else{
			$data['pesan'] = 'register tidak berhasil ';
			$data['status'] = false ;
		}

		echo json_encode($data);






	}

	function insert_request(){

		$iduser = $this->input->post('iduser');
		$idjp = $this->input->post('idjp');
		$lat = $this->input->post('lat');
		$lon = $this->input->post('lon');
		$alamat = $this->input->post('alamat');
		$ket = $this->input->post('ket');

	
		$simpan['order_user'] = $iduser  ;
		$simpan['order_lat'] = $lat ;
		$simpan['order_tanggal'] = date('Y-m-d H:i:s') ;
		$simpan['order_lon'] = $lon ;
		$simpan['order_jp'] = $idjp ; 
		$simpan['booking_ket'] = $ket ;
		$status = $this->db->insert('tb_booking',$simpan);

		if($status){


			$data['pesan'] = 'request berhasil' ;
			$data['status'] = true ;
		
		}
		else {
			$data['pesan'] = 'request tidak berhasil';
			$data['status'] = false ;

		}
		echo json_encode($data);

		




	}



	function login(){

		$hp = $this->input->post('hp');
		$password = $this->input->post('pass');

		$this->db->where('user_telpon',$hp);
		$this->db->where('user_password',$password);

		$result = $this->db->get('tb_user');

		if ($result -> num_rows() > 0 ){
			$data['data'] = $result->row();
			$data['pesan'] = 'login berhasil';
			$data['status'] = true ;
		}
		else {
			$data['pesan'] = 'login tidak berhasil';
			$data['status'] = false ;
		}

			
			echo json_encode($data);

	}

	
	function register_siswa(){

		$name = $this->input->post('name');
		$email = $this->input->post('email');
		$password = $this->input->post('password');
		$hp = $this->input->post('hp');


		$this->db->where('user_email',$email);
		$this->db->where('user_telpon',$hp);

		$check = $this->db->get('tb_user');


			if($check -> num_rows() > 0 ){

				$data['pesan'] = 'email anda udah ke daftar';
				$data['status'] = false ;

				echo json_encode($data);


			}
			else {

				$masuk['user_level'] = 1 ;
		$masuk['user_nama'] = $name ;
		$masuk['user_email'] = $email ;
		$masuk['user_password'] = $password ;
		$masuk['user_telpon'] = $hp ;
		$status = $this->db->insert('tb_user',$masuk);

		if($status){
			$data['pesan'] = 'berhasil register';
			$data['status'] = true ;
		
		}
		else {
			$data['pesan'] = 'register tidak berhasil';
			$data['status'] = false;

		}

		echo json_encode($data);

	}




	}
}
