package org.opensrp.register.mcare.service;

import java.util.ArrayList;
import java.util.List;

import org.opensrp.dto.AclDTO;
import org.opensrp.dto.PrivilegeDTO;
import org.opensrp.dto.RoleDTO;
import org.opensrp.register.mcare.domain.Acl;
import org.opensrp.register.mcare.domain.Privilege;
import org.opensrp.register.mcare.domain.Role;
import org.opensrp.register.mcare.domain.SimplifiedPrivilege;
import org.opensrp.register.mcare.repository.AllRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class RoleService {

	private static Logger logger = LoggerFactory.getLogger(RoleService.class);
	private AllRoles allRoles;
	
	@Autowired
	public RoleService(AllRoles allRoles)
	{
		this.allRoles = allRoles;
	}
	
	public String addRole(RoleDTO roleDTO)
	{		
		logger.info("inside RoleService.addRole");
		Role roleByName = allRoles.findRoleByName(roleDTO.getName());//.findByUserName(roleDTO.getUserName());
		if (roleByName == null) {
			logger.info("No Such Role Found");
			try{
				Role role = new Role();
				//role.withUserName(roleDTO.getUserName());
				role.withName(roleDTO.getName());
				role.withStatus(roleDTO.getStatus());
				List<PrivilegeDTO> privilegeDTOs = roleDTO.getPrivileges();
				List<SimplifiedPrivilege> privileges = new ArrayList<SimplifiedPrivilege>();
				for(int i = 0; i < privilegeDTOs.size(); i++){					
					SimplifiedPrivilege tempSimplifiedPrivilege = new SimplifiedPrivilege();
					tempSimplifiedPrivilege.withName(privilegeDTOs.get(i).getName() == null ? "" : privilegeDTOs.get(i).getName());
					tempSimplifiedPrivilege.withId(privilegeDTOs.get(i).getId() == null ? "" : privilegeDTOs.get(i).getId());
					privileges.add(tempSimplifiedPrivilege);
					logger.info("privilege with name - " +  tempSimplifiedPrivilege.getName() + "added in list");
				}				
				role.withPrivileges(privileges);
				logger.info("privileges added in role object");
				allRoles.add(role);
				return "1";
			}catch(Exception e){
				return "0";
			}
		}else{
			logger.info("Role with same name already exists.");
			return "2";
		}
	}
	
	public String editRole(RoleDTO roleDTO) {		
		Role roles = allRoles.get(roleDTO.getRoleId() );  // null checking isn't done here, should be done rigorously on client-side
		
		if(roles != null){
			try{
				Role role = new Role();
				role.withName(roleDTO.getName());
				role.setId(roleDTO.getRoleId());
				role.setRevision(roles.getRevision());			
				//role.withUserName(roleDTO.getUserName());
				role.withStatus(roleDTO.getStatus());
				List<PrivilegeDTO> privilegeDTOs = roleDTO.getPrivileges();
				List<SimplifiedPrivilege> privileges = new ArrayList<SimplifiedPrivilege>();
				for(int i = 0; i < privilegeDTOs.size(); i++){					
					SimplifiedPrivilege tempSimplifiedPrivilege = new SimplifiedPrivilege();
					tempSimplifiedPrivilege.withName(privilegeDTOs.get(i).getName() == null ? "" : privilegeDTOs.get(i).getName());
					tempSimplifiedPrivilege.withId(privilegeDTOs.get(i).getId() == null ? "" : privilegeDTOs.get(i).getId());
					privileges.add(tempSimplifiedPrivilege);
					logger.info("privilege with name - " +  tempSimplifiedPrivilege.getName() + "added in list");
				}				
				role.withPrivileges(privileges);
				allRoles.update(role);
				return "1";
			}catch(Exception e){
				return "0";
			}			
		}
		else{
			return "2";
		}		
	}
	
	public ArrayList<RoleDTO> getRolesAndUser(){
		List<Role> roles = allRoles.roles();		
		ArrayList<RoleDTO> roleList = new ArrayList<RoleDTO>();
		for (Role role : roles) {
			RoleDTO roleDTO = new RoleDTO()
			.withName(role.getName())
			.withRoleId(role.getId())
			.withStatus(role.getStatus());
			//.withUserName(role.getUserName());			
			roleList.add(roleDTO);			
		}		
		return roleList;
	}
	
	public RoleDTO getRoleByName(String roleName){
		Role role = allRoles.findRoleByName(roleName);
		if(role != null){
			RoleDTO roleDTO = new RoleDTO();
			roleDTO.withName(role.getName());
			roleDTO.withStatus(role.getStatus());
			//roleDTO.wi
			//covert privileges to privilegeDTOs
			return roleDTO;
		}
		else
			return null;
	}
}