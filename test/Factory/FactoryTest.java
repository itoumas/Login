package Factory;

import static org.junit.Assert.*;

import Action.DeleteAction;
import Action.InsertAction;
import Action.UpdateAction;

import org.junit.Before;
import org.junit.Test;


/**
 * Factoryクラスのテストです。
 *
 * @author itoumas
 *
 */
public class FactoryTest {


	Factory fact = null;


	/**
	 * Factoryオブジェクトを作成します。
	 */
	@Before
	public void setUp() {

		fact = new Factory();
	}


	/**
	 * "Delete"が引数である場合です。。
	 * @throws WrongArgumentException
	 */
	@Test
	public void testFactoryOfDelete() throws WrongArgumentException {

		assertTrue("DeleteActionオブジェクトが返ってくるか", fact.factory("Delete") instanceof DeleteAction);
	}


	/**
	 * "Insert"が引数である場合です。
	 * @throws WrongArgumentException
	 */
	@Test
	public void testFactoryOfInsert() throws WrongArgumentException {

		assertTrue("InsertActionオブジェクトが返ってくるか", fact.factory("Insert") instanceof InsertAction);
	}


	/**
	 * "Update"が引数である場合です。
	 * @throws WrongArgumentException
	 */
	@Test
	public void testFactoryOfUpdate() throws WrongArgumentException {

		assertTrue("UpdateActionオブジェクトが返ってくるか", fact.factory("Update") instanceof UpdateAction);
	}


	/**
	 * その他の引数である場合です。
	 * @throws WrongArgumentException
	 */
	@Test
	public void testFactoryException() {

		try {

			fact.factory("Error");

			//factoryメソッドを実行してエラーが発生しなかった場合はテストを失敗させます。
			fail();

		} catch (WrongArgumentException e) {

			//独自の例外が発生した場合はテスト成功
			assertTrue(true);

		}
	}

	//各メソッドにおいて引数を変更するとテスト失敗
}

