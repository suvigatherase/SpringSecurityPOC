
// package com.hook.form.backend.org.controller;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Map.Entry;
// import java.util.stream.Collectors;

// import javax.servlet.http.HttpServletRequest;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.hook.form.backend.org.model.JwtResponse;
// import com.hook.form.backend.org.repo.LoginDetailsRepo;
// import com.hook.form.backend.org.service.LoginService;
// import com.hook.form.backend.org.service.MyUserDetailsService;
// import com.hook.form.backend.org.util.JwtTokenUtil;

// import io.jsonwebtoken.impl.DefaultClaims;

// @CrossOrigin
// @RestController
// @RequestMapping("/refresh")
// public class RefreshController {
//               private String SITE_UP = " 'hook for server org' Site is Up";
//     private String SITE_DOWN = " 'hook for server org' Site is down";
//     private String INCORRECT_URL = "URL is incorrect";

//     @Autowired
// 	//@Qualifier(CustomUserInfoRepo)
//     LoginDetailsRepo loginDetailsRepo;
//     @Autowired
//     LoginService loginService;
//     @Autowired
// 	private JwtTokenUtil jwtTokenUtil;
//     	@Autowired
// 	private MyUserDetailsService userDetailsService;

//     @CrossOrigin
//     @GetMapping(value = "/token")
// 	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
// 		// From the HttpRequest get the claims
// 		DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
//         List<String> auth=null ;

// 		Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
// 		String token = jwtTokenUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
//         String username = jwtTokenUtil.getUsernameFromToken(token);
//         final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//          auth =  userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
// 		return ResponseEntity.ok(new JwtResponse(token, auth));
// 	}

// 	public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
// 		Map<String, Object> expectedMap = new HashMap<String, Object>();
// 		for (Entry<String, Object> entry : claims.entrySet()) {
// 			expectedMap.put(entry.getKey(), entry.getValue());
// 		}
// 		return expectedMap;
// 	}
// }
