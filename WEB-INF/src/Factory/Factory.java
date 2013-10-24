
package Factory;

import Action.DeleteAction;
import Action.EditAction;
import Action.InsertAction;
import Action.UpdateAction;

public class Factory {

	public EditAction factory(String type) throws WrongArgumentException {

		if (type.equals("Delete")) {

			return new DeleteAction();
		}

		if (type.equals("Insert")) {

			return new InsertAction();
		}

		if (type.equals("Update")) {

			return new UpdateAction();
		}

		throw new WrongArgumentException();
	}
}
