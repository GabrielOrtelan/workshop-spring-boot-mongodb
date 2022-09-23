package com.gabrielortelan.workshopmongo.services;

import com.gabrielortelan.workshopmongo.domain.User;
import com.gabrielortelan.workshopmongo.dto.UserDTO;
import com.gabrielortelan.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll(){
        return repo.findAll();
    }

    public User findById(String id){
        //FindOne ou FindByID (Não consegui implementar)
       List<User> users = repo.findAll();
       User user = users.stream()
               .filter(x -> id.equals(x.getId()))
               .findFirst()
               .orElse(null);
       if (user == null){
           throw new RuntimeException("Objeto não encontrado");
       }
       return user;
    }

    public User insert(User obj){
        return repo.insert(obj);
    }

    public void delete(String id){
        findById(id);
        repo.deleteById(id);
    }

    public User update(User obj){
        User newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO objDTO){
        return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
    }

}
