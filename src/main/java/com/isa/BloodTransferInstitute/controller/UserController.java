package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.dto.NewUserDTO;
import com.isa.BloodTransferInstitute.dto.UpdateUserDTO;
import com.isa.BloodTransferInstitute.dto.UserDTO;
import com.isa.BloodTransferInstitute.exception.NotFoundException;
import com.isa.BloodTransferInstitute.mapper.GetUserMapper;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.service.UserService;

import java.util.List;

import javax.validation.Valid;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/User")
public class UserController {

	private final UserService userService;
	private final GetUserMapper getUserMapper;

	@PostMapping("/")
	public ResponseEntity<UserDTO> addUser(@Valid @RequestBody final NewUserDTO dto) {
		final var user = userService.add(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(getUserMapper.entityToDTO(user));
	}

	@PatchMapping("/")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody final UpdateUserDTO dto) {
		final var user = userService.update(dto);
		return ResponseEntity.status(HttpStatus.OK).body(getUserMapper.entityToDTO(user));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") final Long id) {
		userService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("id") final Long id) {
		final var user = userService.get(id);
		return ResponseEntity.status(HttpStatus.OK).body(getUserMapper.entityToDTO(user.get()));
	}

	@GetMapping("/all")
	public ResponseEntity<List<UserDTO>> getAll() {
		final var users = userService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(getUserMapper.entityListToDTOlist(users));
	}

}
