package thelameres.todolist.core.configurations;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import thelameres.todolist.core.data.models.Permission;
import thelameres.todolist.core.data.models.Role;
import thelameres.todolist.core.data.models.User;
import thelameres.todolist.core.repositories.PermissionRepository;
import thelameres.todolist.core.repositories.RoleRepository;
import thelameres.todolist.core.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class StartupInitializationBean implements InitializingBean {
    final
    UserRepository userRepository;
    final
    RoleRepository roleRepository;
    final
    PermissionRepository permissionRepository;
    final
    PasswordEncoder passwordEncoder;


    public StartupInitializationBean(UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void afterPropertiesSet() {

        Permission readTask = checkPermissionInRepository(Permission.builder().name("ReadTask").build());
        Permission writeTask = checkPermissionInRepository(Permission.builder().name("WriteTask").build());
        Permission readUser = checkPermissionInRepository(Permission.builder().name("ReadUser").build());
        Permission writeUser = checkPermissionInRepository(Permission.builder().name("WriteUser").build());
        Role unauthorized = checkRoleInRepository(Role.builder()
                .name("ROLE_UNAUTHORIZED")
                .permission(readUser).build());
        Role user = checkRoleInRepository(Role.builder()
                .name("ROLE_USER")
                .permission(readUser)
                .permission(readTask)
                .parent(unauthorized).build());
        Role moderator = checkRoleInRepository(Role.builder()
                .name("ROLE_MODERATOR")
                .permission(readUser)
                .permission(readTask)
                .permission(writeTask)
                .parent(user).build());
        Role admin = checkRoleInRepository(Role.builder()
                .name("ROLE_ADMIN")
                .permission(readUser)
                .permission(readTask)
                .permission(writeTask)
                .permission(writeUser)
                .parent(moderator).build());
        Role system = checkRoleInRepository(Role.builder()
                .name("ROLE_SYSTEM")
                .permission(readUser)
                .permission(readTask)
                .permission(writeTask)
                .permission(writeUser)
                .parent(admin).build());
    }

    private Permission checkPermissionInRepository(Permission permission) {
        Example<Permission> permissionExample = Example.of(permission);
        if (!permissionRepository.exists(permissionExample)) {
            return permissionRepository.save(permission);
        } else {
            return permissionRepository.findByName(permission.getName()).orElseThrow(EntityNotFoundException::new);
        }
    }

    private Role checkRoleInRepository(Role role) {
        Example<Role> permissionExample = Example.of(role);
        if (!roleRepository.exists(permissionExample)) {
            return roleRepository.save(role);
        } else {
            return roleRepository.findByName(role.getName()).orElseThrow(EntityNotFoundException::new);
        }
    }

    private User checkUserInRepository(User user) {
        Example<User> userExample = Example.of(user);
        boolean b = userRepository.exists(userExample);
        if (!b) {
            return userRepository.save(user);
        } else {
            return userRepository.findByUsername(user.getUsername()).orElseThrow(EntityNotFoundException::new);
        }
    }
}
