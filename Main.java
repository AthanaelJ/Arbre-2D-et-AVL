import java.lang.Math;//javac -g Main.java
import java.util.Random;//java Main

class Main {
  public static void main(String[] args) {
    // long seed=20193776;Param p=new Param(1000, 1000, 200, 10, 0.20, 1.0, 2, seed,
    // false, -1);//erreur des balances
    // long seed=-464959255;Param p=new Param(1000, 1000, 50, 10, 0.20, 0.0, 1,
    // seed, false, 5);//erreur des balances
    /*
     * Random seedGenerateur=new Random();
     * long seed=(long)seedGenerateur.nextInt();
     * //Param p=new Param(1000, 1000, 20, 4, 0.20, 0.0, 5, seed, true, 3);
     * //Param p=new Param(1000, 1000, 50, 4, 0.20, 0.0, 5, 651396468, true, 5);
     * //Param p=new Param(1000, 1000, 60, 20, 0.30, 0.0, 4, 78291749, true, 25);
     * //plage
     * Param p=new Param(1000, 1000, 5000, 1, 0.20, 0.0, 0, seed, false, -1);
     * Strategy s=new Strategy(p);
     * s.genereArbreAleatoire();
     * s.creerImage("zstart 1");
     * s.creerImageS2("zstrat 2");
     */
    int l = 0;
    int h = 0;
    int nbFeuilles = 0;
    int minDimensionCoupe = 0;
    double proportionCoupe = 0;
    double memeCouleurProb = 0;
    int largeurLigne = 0;
    long seed = 0;
    boolean Erreur = false;
    boolean useSeed = false;
    boolean strat2 = false;
    boolean stratGR = false;
    int frec = -1;
    String nom = "untilted";
    try {
      if (args.length >= 7) {
        l = Integer.parseInt(args[0]);
        h = Integer.parseInt(args[1]);
        nbFeuilles = Integer.parseInt(args[2]);
        minDimensionCoupe = Integer.parseInt(args[3]);
        proportionCoupe = Double.parseDouble(args[4]);
        memeCouleurProb = Double.parseDouble(args[5]);
        largeurLigne = Integer.parseInt(args[6]);
        if (args.length > 7) {
          if (!(args[7].equals("n"))) {
            seed = Long.parseLong(args[7]);
            useSeed = true;
          } else {
            Random seedGenerateur = new Random();
            seed = (long) seedGenerateur.nextInt();
          }
        }
        if (args.length > 8) {
          if (args[8].equals("y")) {
            strat2 = true;
          }
        }
        if (args.length > 9) {
          if (args[9].equals("y")) {
            stratGR = true;
          }
        }
        if (args.length > 10) {
          if (stratGR) {
            frec = Integer.parseInt(args[10]);
            if (frec <= 0) {
              Erreur = true;
            }
          }
        }
        if (args.length > 11) {
          nom = args[11];
        }
        if (l <= 0) {
          Erreur = true;
        }
        if (h <= 0) {
          Erreur = true;
        }
        if (nbFeuilles <= 0) {
          Erreur = true;
        }
        if (minDimensionCoupe <= 0) {
          Erreur = true;
        }
        if ((proportionCoupe < 0) || (proportionCoupe >= 0.5)) {
          Erreur = true;
        }
        if ((memeCouleurProb < 0) || (memeCouleurProb > 1)) {
          Erreur = true;
        }
        if ((largeurLigne < 0) || (largeurLigne >= Math.min(l / 2, h / 2))) {
          Erreur = true;
        }
      } else {
        Erreur = true;
      }
    } catch (NumberFormatException e) {
      System.out.println("Erreur:" + e);
      System.out.println(" wrong parameter");
      Erreur = true;
    }
    if (Erreur) {
      System.out.println("Parameters must be :");
      System.out.println("l ∈ ℕ*");
      System.out.println("h ∈ ℕ*");
      System.out.println("nbFeuilles ∈ ℕ*");
      System.out.println("minDimensionCoupe ∈ ℕ*");
      System.out.println("proportionCoupe ∈ [0, 0.5[");
      System.out.println("memeCouleurProb ∈ [0, 1]");
      System.out.println("largeurLigne ∈ [0, min(⌊l/2⌋, ⌊h/2⌋)[");
      System.out.println("(optional) seed ∈ ℝ or \"n\" if you don't want a seed");
      System.out.println("(optional) Strategy 2 ? (y/n)");
      System.out.println("(optional) Strategy \"gros rectangles\" ? (y/n)");
      System.out.println("(optional) the frequancie of \"gros rectangles\" ∈ ℕ* (10 if not specified)");
      System.out.println("(optional) the name of the genreted png");
      System.out.println("if you chose to add an optional you must fill the previous ones");
    } else {
      Param p;
      if (useSeed) {
        p = new Param(l, h, nbFeuilles, minDimensionCoupe, proportionCoupe, memeCouleurProb, largeurLigne, seed,
            stratGR, frec);
      } else {
        p = new Param(l, h, nbFeuilles, minDimensionCoupe, proportionCoupe, memeCouleurProb, largeurLigne);
      }
      Strategy s = new Strategy(p);
      s.genereArbreAleatoire();
      if (strat2) {
        s.creerImageS2(nom);
      } else {
        s.creerImage(nom);
      }
    }
  }
}