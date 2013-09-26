
package Factory;

import Action.EditAction;
import DAO.DeleteAction;
import DAO.InsertAction;
import DAO.UpdateAction;

public class Factory {

	public EditAction factory (String type) {

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
