package com.j7ss.entity;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class InstituicaoTest {

	@Test
	public final void testSave() {
		// TODO
	}

	@Test
	public final void testRemove() {
		 // TODO
	}

	@Test
	public final void testFindAll() {
		List<Instituicao> instituicoes = Instituicao.findAll();
		System.out.println(instituicoes.get(0).getCampus());
	}

	@Test
	public final void testCountAll() {
		 // TODO
	}

	@Test
	public final void testFindByIdUsuario() {
		 // TODO
	}

}
