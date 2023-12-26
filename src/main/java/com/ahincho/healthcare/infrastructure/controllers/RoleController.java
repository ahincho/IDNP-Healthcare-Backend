package com.ahincho.healthcare.infrastructure.controllers;

import com.ahincho.healthcare.application.services.RoleService;
import com.ahincho.healthcare.domain.dtos.RoleRequest;
import com.ahincho.healthcare.domain.dtos.RoleResponse;
import com.ahincho.healthcare.domain.entities.RoleEntity;
import com.ahincho.healthcare.domain.exceptions.RoleDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.RoleNotFoundException;
import com.ahincho.healthcare.domain.mappers.RoleMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<RoleResponse>> getAll() {
        List<RoleEntity> roleEntities = roleService.getAllRoles();
        if (roleEntities.isEmpty()) { return ResponseEntity.noContent().build(); }
        return ResponseEntity.ok(roleEntities.stream().map(RoleMapper::entityToResponse).toList());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RoleResponse> getById(@PathVariable("id") Integer id) throws RoleNotFoundException {
        RoleEntity roleEntity = roleService.getRoleById(id);
        return ResponseEntity.ok(RoleMapper.entityToResponse(roleEntity));
    }
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RoleResponse> save(@RequestBody @Valid RoleRequest roleRequest, UriComponentsBuilder uriComponentsBuilder) throws RoleDuplicatedException {
        RoleEntity roleEntity = roleService.createRole(RoleMapper.requestToEntity(roleRequest));
        URI uri = uriComponentsBuilder.path("/api/roles/{id}").buildAndExpand(roleEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(RoleMapper.entityToResponse(roleEntity));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody @Valid RoleRequest roleRequest) throws RoleNotFoundException, RoleDuplicatedException {
        roleService.updateRole(id, RoleMapper.requestToEntity(roleRequest));
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws RoleNotFoundException {
        roleService.deleteRole(id);
        return ResponseEntity.notFound().build();
    }
}
