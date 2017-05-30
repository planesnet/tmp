 __  __  __  __   _       ____    ____     ____                   _      _     __     __     _      
 \ \/ / |  \/  | | |     |  _ \  |  _ \   / ___|                 | |    / \    \ \   / /    / \     
  \  /  | |\/| | | |     | |_) | | |_) | | |        _____     _  | |   / _ \    \ \ / /    / _ \    
  /  \  | |  | | | |___  |  _ <  |  __/  | |___    |_____|   | |_| |  / ___ \    \ V /    / ___ \   
 /_/\_\ |_|  |_| |_____| |_| \_\ |_|      \____|              \___/  /_/   \_\    \_/    /_/   \_\  
                                                                                                    

   ___    ____     ___     ___                   _      ____    ___ 
  / _ \  |  _ \   / _ \   / _ \                 / \    |  _ \  |_ _|
 | | | | | | | | | | | | | | | |    _____      / _ \   | |_) |  | | 
 | |_| | | |_| | | |_| | | |_| |   |_____|    / ___ \  |  __/   | | 
  \___/  |____/   \___/   \___/              /_/   \_\ |_|     |___|
                                                                    


Esta api esta basada en la clase Object de Java (https://www.odoo.com/documentation/10.0/api_integration.html).

Todos los llamados devuelven datos tipo Object[] o Boolean para el caso del delete y el update.

Metodos:

- Constructor: Devuelve un objecto RpcHandler que representa una instancia de la clase/libreria.
		llamado: RpcHandler clase = new RpcHandler("http://xxxxx.com", "test", "admin", "admin");
		params: Url, Nombre de la bd, user, pass.
		resultado: Un objeto RpcHandler.

- Search: Devuelve un objecto Object[] que representa una lista de int con las ids del modelo
		llamado: clase.search("note.note", new Object[] { new Object[]{"id", "=", 11}});
		params: Nombre del modelo, Parametros de busqueda.
		resultado: devuelve todas las note con id = 11.

- Read: Devuelve un objecto Object[] que representa una lista en cadena con los campos requeridos
		llamado: clase.read("note.note", new Object[] { new Object[]{"id", "=", 11}}, new Object[]{"id", "color", "display_name"});
		params: Nombre del modelo, Parametros de busqueda, Campos se desean en el resultado.
		resultado: Devuelve los campos color y display_name de todos los objetos notas con el id = 11.

- SearchRead: Devuelve un objecto Object[] que representa una lista en cadena con los campos requeridos
		llamado: clase.searchRead("note.note", new Object[] {}, new Object[]{"id", "color", "display_name"});
		params: Nombre del modelo, Parametros de busqueda, Campos se desean en el resultado.
		resultado: Devuelve los campos id, color y display_name de todos los objetos notas.

- Create: Crea y devuelve un objecto Object[] que representa un identificador del objeto en la bd
		llamado: HashMap<String, Object> values = new HashMap<String, Object>();
				 values.put("color", 6);
				 clase.create("note.note", values);
		params: Nombre del modelo, Parametros de creacion.
		resultado: Crea una nota con el color 6 y Devuelve el id.

- Update: Actualiza y devuelve un booleano que representa si actualizo o no el objeto en la bd
		llamado: HashMap<String, Object> values = new HashMap<String, Object>();
				 values.put("color", 5);
				 clase.update("note.note", new Object[] { new Object[]{"id", "=", 11}}, values);
		params: Nombre del modelo, Parametros de busquedad, Valores a cambiar.
		resultado: Actualiza la nota 11, cambia su color a numero 5 y devuelve true si lo logro.

- Delete: Borra y devuelve un booleano que representa si borro o no el objeto en la bd
		llamado: clase.delete("note.note", new Object[] { new Object[]{"id", "=", 11}});
		params: Nombre del modelo, Parametros de busquedad.
		resultado: Borra la nota 11 y devuelve true si lo logro.


