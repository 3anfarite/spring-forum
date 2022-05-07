package org.example.again.services;

import java.util.List;
import java.util.Optional;

import org.example.again.Entities.Pic;
import org.example.again.Entities.Post;
import org.example.again.Entities.User;
import org.example.again.repositories.PicRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class PicStorageService {
	
	@Autowired
	private PicRepository picRepository;

	public void store(MultipartFile file,User user,Post post) {

		String picname = file.getOriginalFilename();
		try { 
			Pic pic = new Pic(picname,file.getContentType(),file.getBytes());
			pic.setUser(user);
			pic.setPost(post);
			picRepository.save(pic);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Pic getFile(Integer fileId) {
		  return picRepository.findById(fileId).orElse(null);
	  }
	
	public Pic findPictureByName(String picName) {
		return picRepository.findPicBypicName(picName);}
	
	
	public Pic getImageById (Integer id) {
		return picRepository.findById(id).orElse(null);
	}
	public List<Pic> getAllActivePics() {
		return picRepository.findAll();
	}
	public List <Pic> finduserPic(User user) {
		  return picRepository.findByUser(user);
	}
	
}