package Shop.repositories;

import Shop.entities.UserEntity;
import Shop.entities.UserRoleEntity;
import Shop.entities.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRolePK> {
    List<UserRoleEntity> findByUser(UserEntity user);
}
