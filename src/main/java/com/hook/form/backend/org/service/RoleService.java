// package com.hook.form.backend.org.service;

// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collector;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.hook.form.backend.org.model.Role;
// import com.hook.form.backend.org.repo.RoleRepo;

// @Service
// public class RoleService {
//     @Autowired
// 	//@Qualifier(CustomUserInfoRepo)
//     RoleRepo roleRepo;
//    public List<Role> findByName(List<String> name)
//    {
//         List <Role> userroles= name.stream().map(role -> roleRepo.findByName(role).get()).collect(Collectors.toList());
      
//        return userroles;
//    }


// }
