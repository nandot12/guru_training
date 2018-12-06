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

	
	function login_siswa(){

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


		$masuk['user_nama'] = $name ;
		$masuk['user_email'] = $email ;
		$masuk['user_password'] = $password ;
		$masuk['user_telpon'] = $hp ;
		$status = $this->db->insert('tb_user',$masuk);

		if($status){
			$data['pesan'] = 'berhasil login';
			$data['status'] = true ;
			$data['data'] = $status->row();
		}
		else {
			$data['pesan'] = 'login tidak berhasil';
			$data['status'] = false;

		}

		echo json_encode($data);

	}




	}
}
