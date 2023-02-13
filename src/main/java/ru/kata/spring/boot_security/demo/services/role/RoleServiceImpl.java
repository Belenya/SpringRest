package ru.kata.spring.boot_security.demo.services.role;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.user.UserService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements UserService<Role, Long> {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void save(Role o) {
        roleRepository.save(o);
    }

    @Override
    public Optional<Role> findById(Long aLong) {
        return roleRepository.findById(aLong);
    }

    @Override
    @Transactional
    public void update(Role o) {
        roleRepository.save(o);
    }

    @Override
    @Transactional
    public void deleteById(Long aLong) {
        roleRepository.deleteById(aLong);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
