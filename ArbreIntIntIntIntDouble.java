import java.awt.Color;
/**
 *Classe ArbreIntIntIntIntDouble.
 *Caractérisée par :
 *un Arbre2d
 *4 entiers
 *un réel
 */
public class ArbreIntIntIntIntDouble{
	//Attributs
	private Arbre2d a;
	private int i1;
	private int i2;
	private int i3;
	private int i4;
  private double d;
	//Méthodes

  /**
   *Constructeur par défaut de ArbreIntIntIntIntDouble
   */
	public ArbreIntIntIntIntDouble(){
		a=new Arbre2d(Color.PINK);
		i1=0;
		i2=0;
		i3=0;
		i4=0;
		d=-1;
	}

  /**
   *Constructeur de ArbreIntIntIntIntDouble
   *
   *@param arbre un Arbre2d
   *@param int1 un entier
   *@param int2 un entier
   *@param int3 un entier
   *@param int4 un entier
   *@param val un réel
   */
	public ArbreIntIntIntIntDouble(Arbre2d arbre, int int1, int int2, int int3, int int4, double val){
		a=arbre;
		i1=int1;
		i2=int2;
		i3=int3;
		i4=int4;
		d=val;
	}

  /**
   *getter retournant l'Arbre2d
   *
   *@return Arbre2d
   */
	public Arbre2d getArbre(){
		return a;
	}

  /**
   *getter retournant le 1er entier
   *
   *@return entier
   */
	public int getInt1(){
		return i1;
	}

  /**
   *getter retournant le 2e entier
   *
   *@return entier
   */
	public int getInt2(){
		return i2;
	}

  /**
   *getter retournant le 3e entier
   *
   *@return entier
   */
	public int getInt3(){
		return i3;
	}

  /**
   *getter retournant le 4e entier
   *
   *@return entier
   */
	public int getInt4(){
		return i4;
	}

  /**
   *getter retournant d
   *
   *@return réel
   */
	public double getDouble(){
		return d;
	}

  /**
   *setter affectant la valeur de d
   *
   *@param donnee un réel
   */
	public void setDouble(double donnee){
		d=donnee;
	}
}