package it.handart.backend.api;

import it.handart.backend.business.BusinessException;
import it.handart.backend.business.HandArtUserService;
import it.handart.backend.common.spring.security.JWTTokenUtil;
import it.handart.backend.common.spring.security.UserDetailsImpl;
import it.handart.backend.domain.rest.Utente;

import it.handart.backend.domain.rest.response.UtenteResponse;
import it.handart.backend.domain.rest.response.UtenteResponseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class UserController {

	@Value("${jwt.token.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTTokenUtil jwtTokenUtil;

	@Autowired
	private HandArtUserService handArtUserService;

	@PostMapping("/login")
	public UtenteResponseToken login(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws AuthenticationException {
		// Effettuo l'autenticazione
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Genero Token e lo inserisco nell'header di risposta
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String token = jwtTokenUtil.generateToken(userDetails);
		response.setHeader(tokenHeader, token);

		System.out.println(response.getHeader(tokenHeader));

		// Ritorno l'utente
		return new UtenteResponseToken(((UserDetailsImpl) userDetails).getUtente(), token);
	}

	@PostMapping("/utente/updateprofilo")
	public UtenteResponse updateProfilo(@RequestBody Utente utente) {
		Utente nuovoUtente = handArtUserService.updateProfilo(utente);
		return new UtenteResponse(nuovoUtente);
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUtente(@RequestBody Utente utente) {

		try {
			Utente nuovoUtente = handArtUserService.registerUtente(utente);
			return ResponseEntity
					.status(HttpStatus.FORBIDDEN)
					.body("Registered correctly "+ nuovoUtente.getUsername());
		}catch(BusinessException e) {
			return ResponseEntity
					.status(HttpStatus.FORBIDDEN)
					.body("Error");
		}
	}
}
