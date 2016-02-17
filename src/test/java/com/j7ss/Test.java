package com.j7ss;

public class Test {

	
	public static void main(String[] args) {
		String a = "### Usuário Nome: &&USUARIO_NOME&&"+
"### Usuário Email: &&USUARIO_EMAIL&&";
		
		
		
		System.out.println(a.replace("&&USUARIO_NOME&&", "AAAA"));
	}
}
