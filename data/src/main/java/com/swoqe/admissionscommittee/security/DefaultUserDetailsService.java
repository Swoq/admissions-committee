package com.swoqe.admissionscommittee.security;

import com.swoqe.admissionscommittee.dao.institution.InstitutionJpaRepository;
import com.swoqe.admissionscommittee.entity.ApplicantEntity;
import com.swoqe.admissionscommittee.entity.InstitutionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService, AuthService {

    private final UserJpaRepository userRepository;
    private final InstitutionJpaRepository institutionRepository;
    private final PasswordEncoder passwordEncoder;
    private final IAuthenticationFacade authenticationFacade;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ApplicantEntity user = userRepository.findBySecurityUserEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return user.getSecurityUser();
    }

    @Override
    @Transactional
    @PreAuthorize("isAnonymous()")
    public void signUpUser(@Valid RegistrationRequest request) {
        ApplicantEntity plainUserEntity = createPlainUserEntity(request);
        InstitutionEntity institutionEntity = institutionRepository.findById(request.getInstitutionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        plainUserEntity.setInstitution(institutionEntity);
        userRepository.save(plainUserEntity);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('admin:write')")
    public void blockUserByEmail(String email) {
        ApplicantEntity applicant = userRepository.findBySecurityUserEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        applicant.getSecurityUser().setLocked(true);
        userRepository.save(applicant);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('admin:write')")
    public void unblockUserByEmail(String email) {
        ApplicantEntity applicant = userRepository.findBySecurityUserEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        applicant.getSecurityUser().setLocked(false);
        userRepository.save(applicant);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('user:read')")
    public Optional<ApplicantEntity> getCurrentUser() {
        return authenticationFacade.getAuthenticationUser();
    }

    private ApplicantEntity createPlainUserEntity(RegistrationRequest request) {
        SecurityUserDetails securityUserDetails = new SecurityUserDetails();
        securityUserDetails.setId(UUID.randomUUID());
        securityUserDetails.setPassword(passwordEncoder.encode(request.getPassword()));
        securityUserDetails.setUserRole(UserRole.PLAIN_USER);
        securityUserDetails.setEmail(request.getEmail());

        ApplicantEntity user = new ApplicantEntity();
        user.setId(UUID.randomUUID());
        user.setSecurityUser(securityUserDetails);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        return user;
    }

}
