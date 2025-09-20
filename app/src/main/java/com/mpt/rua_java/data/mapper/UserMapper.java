package com.mpt.rua_java.data.mapper;

import com.mpt.rua_java.data.remote.dto.*;
import com.mpt.rua_java.data.local.entity.UserEntity;
import com.mpt.rua_java.domain.entity.*;

/**
 * Mappers para convertir entre DTOs, entidades de dominio y entidades Room
 * Implementan el patrón Mapper para aislar las capas de Clean Architecture
 */
public class UserMapper {

    // DTO -> Domain Entity
    public static User toDomainEntity(UserDto dto) {
        return new User(
            dto.getLogin().getUuid(),
            dto.getGender(),
            toDomainEntity(dto.getName()),
            toDomainEntity(dto.getLocation()),
            dto.getEmail(),
            toDomainEntity(dto.getLogin()),
            toDomainEntity(dto.getDob()),
            toDomainEntity(dto.getRegistered()),
            dto.getPhone(),
            dto.getCell(),
            toDomainEntity(dto.getPicture()),
            dto.getNat(),
            false  // Inicializar explícitamente como NO agregado a contactos
        );
    }

    public static Name toDomainEntity(NameDto dto) {
        return new Name(dto.getTitle(), dto.getFirst(), dto.getLast());
    }

    public static Location toDomainEntity(LocationDto dto) {
        return new Location(
            toDomainEntity(dto.getStreet()),
            dto.getCity(),
            dto.getState(),
            dto.getCountry(),
            dto.getPostcode(),
            toDomainEntity(dto.getCoordinates()),
            toDomainEntity(dto.getTimezone())
        );
    }

    public static Street toDomainEntity(StreetDto dto) {
        return new Street(dto.getNumber(), dto.getName());
    }

    public static Coordinates toDomainEntity(CoordinatesDto dto) {
        return new Coordinates(dto.getLatitude(), dto.getLongitude());
    }

    public static Timezone toDomainEntity(TimezoneDto dto) {
        return new Timezone(dto.getOffset(), dto.getDescription());
    }

    public static Login toDomainEntity(LoginDto dto) {
        return new Login(
            dto.getUuid(),
            dto.getUsername(),
            dto.getPassword(),
            dto.getSalt(),
            dto.getMd5(),
            dto.getSha1(),
            dto.getSha256()
        );
    }

    public static DateOfBirth toDomainEntity(DateOfBirthDto dto) {
        return new DateOfBirth(dto.getDate(), dto.getAge());
    }

    public static Registered toDomainEntity(RegisteredDto dto) {
        return new Registered(dto.getDate(), dto.getAge());
    }

    public static Picture toDomainEntity(PictureDto dto) {
        return new Picture(dto.getLarge(), dto.getMedium(), dto.getThumbnail());
    }

    // Domain Entity -> Room Entity
    public static UserEntity toRoomEntity(User user) {
        return new UserEntity(
            user.getId(),
            user.getGender(),
            user.getName().getTitle(),
            user.getName().getFirst(),
            user.getName().getLast(),
            user.getLocation().getStreet().getNumber(),
            user.getLocation().getStreet().getName(),
            user.getLocation().getCity(),
            user.getLocation().getState(),
            user.getLocation().getCountry(),
            user.getLocation().getPostcode(),
            user.getLocation().getCoordinates().getLatitude(),
            user.getLocation().getCoordinates().getLongitude(),
            user.getLocation().getTimezone().getOffset(),
            user.getLocation().getTimezone().getDescription(),
            user.getEmail(),
            user.getLogin().getUuid(),
            user.getLogin().getUsername(),
            user.getLogin().getPassword(),
            user.getLogin().getSalt(),
            user.getLogin().getMd5(),
            user.getLogin().getSha1(),
            user.getLogin().getSha256(),
            user.getDob().getDate(),
            user.getDob().getAge(),
            user.getRegistered().getDate(),
            user.getRegistered().getAge(),
            user.getPhone(),
            user.getCell(),
            user.getPicture().getLarge(),
            user.getPicture().getMedium(),
            user.getPicture().getThumbnail(),
            user.getNat(),
            user.isAddedToContacts()
        );
    }

    // Room Entity -> Domain Entity
    public static User toDomainEntity(UserEntity entity) {
        return new User(
            entity.getId(),
            entity.getGender(),
            new Name(entity.getNameTitle(), entity.getNameFirst(), entity.getNameLast()),
            new Location(
                new Street(entity.getStreetNumber(), entity.getStreetName()),
                entity.getCity(),
                entity.getState(),
                entity.getCountry(),
                entity.getPostcode(),
                new Coordinates(entity.getCoordinatesLatitude(), entity.getCoordinatesLongitude()),
                new Timezone(entity.getTimezoneOffset(), entity.getTimezoneDescription())
            ),
            entity.getEmail(),
            new Login(
                entity.getLoginUuid(),
                entity.getLoginUsername(),
                entity.getLoginPassword(),
                entity.getLoginSalt(),
                entity.getLoginMd5(),
                entity.getLoginSha1(),
                entity.getLoginSha256()
            ),
            new DateOfBirth(entity.getDobDate(), entity.getDobAge()),
            new Registered(entity.getRegisteredDate(), entity.getRegisteredAge()),
            entity.getPhone(),
            entity.getCell(),
            new Picture(entity.getPictureLarge(), entity.getPictureMedium(), entity.getPictureThumbnail()),
            entity.getNat(),
            entity.isAddedToContacts()
        );
    }
}
