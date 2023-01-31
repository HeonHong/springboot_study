package user.service;

import java.util.List;

import user.bean.UserDTO;

public interface UserService {

	public void write(UserDTO userDTO);

	/* public String getUser(String id); */

	public List<UserDTO> getList();

	public String isExistId(String id);

	public UserDTO getUser(String id);

	public void update(UserDTO userDTO);

	public void delete(String id);



}
