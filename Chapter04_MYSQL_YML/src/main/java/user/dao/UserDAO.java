package user.dao;

import java.util.List;

import user.bean.UserDTO;

public interface UserDAO {
	public void write(UserDTO userDTO);

	public UserDTO getUser(String id);

	public List<UserDTO> getList();

	public void update(UserDTO userDTO);

	public void delete(String id);

}
