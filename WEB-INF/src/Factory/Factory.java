
package Factory;

import Action.DeleteAction;
import Action.EditAction;
import Action.InsertAction;
import Action.UpdateAction;

public class Factory {

<<<<<<< HEAD
	public EditAction factory(String type) {
=======
	public EditAction factory (String type) {
>>>>>>> ba8539b9f620218559731891a1db7d8773abb637

		if (type.equals("Delete")) {

			return new DeleteAction();
		}

		if (type.equals("Insert")) {

			return new InsertAction();
		}

		if (type.equals("Update")) {

			return new UpdateAction();
		}

		return new DeleteAction();
	}
}
