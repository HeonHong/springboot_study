package user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import user.bean.UserDTO;
import user.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void write(UserDTO userDTO) {
		//DB
		userDAO.write(userDTO);
		
	}

	@Override
	public List<UserDTO> getList() {
		
		return userDAO.getList();
	}

	@Override
	public String isExistId(String id) {
		//DB
		UserDTO userDTO = userDAO.getUser(id);
		if(userDTO==null) {
			return "non_exist";
		}else{
			return "exist";
		}
		
		
	}

	@Override
	public UserDTO getUser(String id) {
		//DB
		return userDAO.getUser(id);
	}

	@Override
	public void update(UserDTO userDTO) {
		userDAO.update(userDTO);
		
	}


	@Override
	public void delete(String id) {
		userDAO.delete(id);
		
	}

	
	
	/*
	@Override
	public String getUser(String id) {
		UserDTO userDTO=userDAO.getUser(id);
		String answer;
		
		if(userDTO!=null) {
			answer = "true";
		}else{
			answer = "false";
		}
		
		return answer;
	}
	*/
	
	

}
