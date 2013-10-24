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
	 */
	@Test
	public void testFactoryOfDelete() {

		assertTrue("DeleteActionオブジェクトが返ってくるか", fact.factory("Delete") instanceof DeleteAction);
	}


	/**
	 * "Insert"が引数である場合です。
	 */
	@Test
	public void testFactoryOfInsert() {

		assertTrue("InsertActionオブジェクトが返ってくるか", fact.factory("Insert") instanceof InsertAction);
	}


	/**
	 * "Update"が引数である場合です。
	 */
	@Test
	public void testFactoryOfUpdate() {

		assertTrue("UpdateActionオブジェクトが返ってくるか", fact.factory("Update") instanceof UpdateAction);
	}


	/**
	 * その他の引数である場合です。
	 */
	@Test
	public void testFactory() {

		assertTrue("DeleteActionオブジェクトが返ってくるか", fact.factory("Nothing") instanceof DeleteAction);
	}

	//各メソッドにおいて引数を変更するとテスト失敗
}

