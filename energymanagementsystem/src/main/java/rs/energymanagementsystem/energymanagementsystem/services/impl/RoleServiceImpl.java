package rs.energymanagementsystem.energymanagementsystem.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.Role;
import rs.energymanagementsystem.energymanagementsystem.repositories.RoleRepository;
import rs.energymanagementsystem.energymanagementsystem.services.RoleService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private Role role;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
