commande de compilation et éxécution : <br />
javac -g Main.java <br />
<br />
<br />
java Main l h nbFeuilles minDimensionCoupe proportionCoupe memeCouleurProb largeurLigne <br />
ou <br />
java Main l h nbFeuilles minDimensionCoupe proportionCoupe memeCouleurProb largeurLigne seed strategy2? strategyGrosRectangule? frequancie name <br />
<br />
<br />
où les paramètres sont :
l ∈ ℕ*
h ∈ ℕ*
nbFeuilles ∈ ℕ*
minDimensionCoupe ∈ ℕ*
proportionCoupe ∈ [0, 0.5[
memeCouleurProb ∈ [0, 1]
largeurLigne ∈ [0, min(⌊l/2⌋, ⌊h/2⌋)[
(optionel) seed ∈ ℝ ou "n" si on ne veut pas de seed
(optionel) Strategy 2 ? (y/n)
(optionel) Strategy "gros rectangles" ? (y/n)
(optionel) la fréquance des "gros rectangles" ∈ ℕ* (10 si pas spécifié)
(optionel) name le nom de l'image généré