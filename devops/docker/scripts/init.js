db.createUser(
	{
		user: 'user',
		pwd: 'password',
		roles: [{role: 'readWrite', db: 'miniautorizador'}]
	}
);

db.createCollection('usuario');

db.usuario.insertMany([
	{
		name: 'Admin',
		email: 'desafio@email.com',
		password: '$2a$10$L5gEAOuUlArOvaQ83VQmZux.Cxw7yUIJCKHWh6z9Gf/uWifGR0U/m',
		profile: 'ROLE_ADM',
		active: true,
		createdDate: new Date()
	}
]);