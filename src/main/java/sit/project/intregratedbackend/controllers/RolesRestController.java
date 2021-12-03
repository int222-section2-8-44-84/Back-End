package sit.project.intregratedbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import sit.project.intregratedbackend.models.Roles;
import sit.project.intregratedbackend.repositories.RolesRepository;

@CrossOrigin
@RestController
public class RolesRestController {

	@Autowired
	private RolesRepository roleRepo;
	
	@GetMapping("/showAllRole")
	public List<Roles> showAllRole(){
		return roleRepo.findAll();
	}
	
    @GetMapping("/roles/{roleID}")
    public Roles showRole(@PathVariable String roleID) {
        return roleRepo.findById(Integer.parseInt(roleID)).orElse(null);
    }
	
}
