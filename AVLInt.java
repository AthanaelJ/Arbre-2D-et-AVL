/**
 *Classe AVLInt.
 *Caractérisée par :
 *un AVL
 *un entier
 */
public class AVLInt{
	//Attributs
	private AVL a;
  private int v;
	//Méthodes

	/**
	 *Constructeur AVLInt
	 *
	 *@param arbre un AVL
	 *@param var un entier
	 */
	public AVLInt(AVL arbre, int var){
		a=arbre;
		v=var;
	}

  /**
   *getter retournant l'AVL
   *
   *@return AVL
   */
	public AVL getAVL(){
		return a;
	}
  
  /**
   *getter retournant l'entier stocké
   *
   *@return entier
   */
	public int getVar(){
		return v;
	}

}