package com.example.apimongo.services;

import com.example.apimongo.exceptions.NoResultException;
import com.example.apimongo.models.Profile;
import com.example.apimongo.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
/**
 * Un componente que contenga el formulario con las validaciones correspondientes y botón de agregar perfil. El botón de agregar perfil debe estar deshabilitado si no cumple con las validaciones mencionadas
 * Un componente para editar el perfil, deberá mantener las mismas validaciones que las de la creación del formulario, todos los campos podrán ser editados. Agregar un botón para regresar  a la página principal.
 * Un componente que muestra los detalles de un perfil (Nombre, apellido paterno, apellido materno, fecha de nacimiento (dd/mm/YYYY), edad y foto). Agregar un botón para regresar  a la página principal.
 * Un componente principal que gestionará los perfiles con la siguientes operaciones:
 * - Debe mostrar la lista de perfiles con los campos: foto y nombre, agregar link o botón para las acciones de detalle, eliminar y editar.
 * - Agregar tres campos para filtrar la lista de perfiles por (nombre, apellido paterno y apellido materno). La lista se deberá actualizar dinámicamente con estos filtros en caso de coincidencias, si no se proporcionan filtros la lista se mostrará completa)  (Opcional, será un plus)
 * - Dar alta un nuevo perfil (aunque  el evento se dispare en el componente de formulario, este deberá ser escuchado y ejecutado finalmente en este componente)
 * - Editar un perfil (aunque  el evento se dispare en el componente de edición de perfil, este deberá ser escuchado y ejecutado finalmente en este componente)
 * - Eliminar un perfil al dar clic al link o botón eliminar
 * - Permitir visualizar el detalle de cada registro al dar click en el link o botón de  detalles.
 */
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    private static final int PAGE_RESULT_SIZE = 10;

    public Profile saveProfile(final Profile profile) {
        profile.setId(null);
        return this.profileRepository.save(profile);
    }

    public Profile updateProfile(final String id, final Profile updateRequest) throws NoResultException {
        Profile profile = this.findProfileById(id);
        profile.setId(id);
        profile.setName(updateRequest.getName());
        profile.setFatherLastname(updateRequest.getFatherLastname());
        profile.setMotherLastname(updateRequest.getMotherLastname());
        profile.setBirthDate(updateRequest.getBirthDate());
        profile.setAge(updateRequest.getAge());
        return this.profileRepository.save(profile);
    }

    public Profile findProfileById(final String id) throws NoResultException {
        return this.profileRepository.findById(id).orElseThrow(() -> new NoResultException("Profile not found"));
    }

    public Map<String, Object> listProfiles(final Integer page) {
        Pageable paging = PageRequest.of(page, PAGE_RESULT_SIZE);
        Page<Profile> pageProfile = this.profileRepository.findAll(paging);

        Map<String, Object> response = new HashMap<>();
        response.put("profiles", pageProfile.getContent());
        response.put("currentPage", pageProfile.getNumber());
        response.put("totalItems", pageProfile.getTotalElements());
        response.put("totalPages", pageProfile.getTotalPages());
        return response;
    }

    public void deleteProfile(final String id) {
        this.profileRepository.deleteById(id);
    }

}
