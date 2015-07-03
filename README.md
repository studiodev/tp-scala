Projet forké depuis le dépot de Julien Richard-foy, disponible ici : https://github.com/julienrf/tp-scala

Les slides du cours sont disponibles [ici](https://docs.google.com/presentation/pub?id=1SVifmpAPDSc3_gFuXciPXCtE-N-TawARWk_uBjXop1k&start=false&loop=false&delayms=3000).

L’objectif de ce TP est de prendre en main le langage Scala et de se familiariser avec ses idiomes.

Téléchargez le [code du TP](https://github.com/studiodev/tp-scala/zipball/master). L’archive contient un projet Scala utilisant [sbt](https://github.com/harrah/xsbt/wiki) (équivalent de maven). Lancez la commande `$ sbt` à la racine du projet. Vous entrez alors dans l’environnement sbt, permettant de lancer des commandes similaires aux commandes maven, pour compiler le projet, le tester ou le lancer.

Pour importer le projet dans IntelliJ (recommandé):
```
> gen-idea
```
Puis, dans IntelliJ, File −> Open Project….


Pour importer le projet dans Eclipse  :
```
> eclipse
```
Puis, dans Eclipse, File −> Import −> Existing project into workspace….

Pour utiliser la console interactive Scala (REPL) :
```scala
> console
[info] Starting scala interpreter...
[info]
Welcome to Scala version 2.10.0-RC1 (Java HotSpot(TM) 64-Bit Server VM, Java 1.6.0_35).
Type in expressions to have them evaluated.
Type :help for more information.

scala> val opower = new tp.OPower
opower: tp.OPower = tp.OPower@38942215

scala> opower.energy("julien")
res0: Int = 1953
```
Utilisez `Ctrl+D` pour sortir de la console (nécessaire pour relancer une console afin de prendre en compte les éventuelles modifications que vous aurez effectuées sur le code source).

Pour compiler le projet :
```scala
> compile
```

Pour lancer le projet :
```scala
> run
```

Vous pouvez aussi demander à sbt de recompiler votre projet à chaque fois que vous modifiez les sources en préfixant votre commande par `~`. Par exemple, la commande `> ~run` relance le projet à chaque modification des sources.

# Fonctions

## Boucles fonctionnelles

Passage obligé pour prendre en main un langage fonctionnel : le calcul de la [suite de Fibonacci](http://en.wikipedia.org/wiki/Fibonacci_number). Les deux premiers termes de cette suite sont 0 et 1, puis chaque terme suivant est la somme des deux précédents : 0, 1, 1, 2, 3, 5, 8, 13, etc.

**Question 1** : Implémentez la fonction `fibonacci` qui renvoie le `n`ième terme de la suite de Fibonacci. Par exemple, `fibonacci(3)` doit retourner `2`. (Un conseil, lors de vos tests n’essayez pas avec des valeurs supérieures à 50.)

**Question 2** (facultative) : Implémentez `fibonacciRec`, une implémentation récursive terminale du calcul d’un terme de la suite de Fibonacci. Inspirez-vous de la solution utilisée en cours pour rendre `fact` récursive terminale : définissez une sous-fonction `loop` qui effectue un « pas » de calcul et qui prend en paramètre le nombre restant d’itérations à effectuer et un « accumulateur » contenant les données issues de l’itération précédente. Dans votre cas, vous utiliserez probablement deux accumulateurs : un pour le terme courant de la suite de Fibonacci et un pour le terme suivant.

## Fonctions d’ordre supérieur

Le programme `show` affiche une liste de nombres sur la console :
```scala
def show(xs: Int*) {
  for (x <- xs) {
    println(x)
  }
}
```
Le programme `showSquares` affiche les carrés d’une liste de nombres sur la console :
```scala
def showSquares(xs: Int*) {
  for (x <- xs) {
    println(x * x)
  }
}
```
Observez comme les programmes `show` et `showSquares` sont similaires : leur seule différence réside dans la façon dont le nombre va être transformé avant d’être affiché. Peut-on factoriser la parties communes de ces deux programmes ? Pour cela il faut transformer ce qui les différencie en un paramètre. Dans notre cas, il s’agit d’une fonction qui transforme un nombre en un autre nombre.

**Question 3** : Implémentez la fonction d’ordre supérieur `show(f: Int => Int, xs: Int*)`, qui affiche tous les nombres `xs` après les avoir transformés avec la fonction `f`. Exemples d’utilisation :
```scala
show(x => x, 1, 2, 3)
show(x => x * x, 1, 2, 3)
```

# Structures de données immuables

Une structure de données immuable est une structure de données qui n’est pas modifiable : toute opération de mise à jour retourne une nouvelle instance contenant la mise à jour. Dans le cas d’une liste, par exemple, l’ajout d’un élément retourne une nouvelle liste contenant l’élément ajouté et tous les autres éléments précédemment contenus.

**Question 4** : Expliquez comment l’utilisation de structures de données immuables peut être efficace (comment éviter la recopie des données) et quelles contraintes cela implique sur les données contenues. Quels sont d’après vous les avantages des structures de données immuables ?

***

Une liste chaînée est une structure de données permettant de représenter une séquence ordonnée d’éléments. Une façon simple de la représenter est de considérer deux cas possibles :
* une liste vide ;
* un élément suivi d’une fin de liste (pouvant elle-même être soit une liste vide, soit un élément suivi d’une fin de liste, et ainsi de suite… Il s’agit d’une définition récursive).

Supposons qu’il existe une fonction `cons` qui permet de construire une liste à partir d’un élément et de la fin de liste, et une fonction `nil` qui retourne une liste vide, la liste des entiers de 1 à 3 peut s’écrire :

```scala
cons(1, cons(2, cons(3, nil)))
```

Le diagramme d’objets correspondant serait le suivant :

![Diagramme d’objets](https://github.com/julienrf/tp-scala/raw/master/list.png)

Dans le code du TP le fichier `src/main/scala/tp/IntList.scala` définit une classe abstraite `IntList` représentant une liste de nombres. Cette classe ne contient que des définitions d’opérations abstraites, à implémenter dans les sous-classes `Nil` (liste vide) et `Cons` (liste contenant un nombre `head` et une fin de liste `tail`). Le fait que le type `IntList` soit récursif vous permet de raisonner par induction pour l’implémentation de chaque méthode dans les classes `Nil` et `Cons` :

1. `Nil`: implémentation dans le cas de la liste vide ;
2. `Cons(head, tail)`: implémentation traitant l’élément `head` (par hypothèse d’induction l’implémentation pour `tail` est déjà faite).

Remarquez comme le découpage au niveau des types de données vous aide ensuite à découper les implémentations en problèmes plus simples. L’implémentation d’une opération sur `IntList` est définie par la **somme** des implémentations pour `Nil` et `Cons`. L’utilisation d’un terme emprunté au champ lexical de l’<strong>algèbre</strong> n’est pas anodin car, tel des Jourdains de la programmation fonctionnelle, vous manipulez des [types **algébriques**](http://en.wikipedia.org/wiki/Algebraic_data_type) : le type `IntList` est la somme des types `Nil` et `Cons`.

***

Un objet `IntList` fournit plusieurs *factory methods* pour créer des listes. Vous pouvez l’utiliser dans la console comme suit:
```scala
scala> import tp.IntList
import tp.IntList

scala> IntList(1, 2, 3)
res0: tp.IntList = cons(1, cons(2, cons(3, nil)))
```

**Remarque** : Quasiment toutes les réponses aux questions qui suivent tiennent en une ligne. Si votre implémentation fait plus que ça, réfléchissez encore.

**Question 5** : Modifiez la méthode `toString` pour qu’elle produise un résultat conforme aux exemples suivants :
```scala
scala> IntList().toString
res0: String = nil

scala> IntList(1).toString
res1: String = 1 :: nil

scala> IntList(1, 2).toString
res2: String = 1 :: 2 :: nil
```

**Question 6** : Implémentez la méthode `foreach(f: Int => Unit): Unit`, appliquant la fonction `f` à tous les éléments de la liste. Exemple :
```scala
scala> IntList(1, 2, 3).foreach(x => println(x % 2 == 1))
true
false
true
```

**Question 7** : Implémentez `map(f: Int => Int): IntList`, retournant une liste contenant tous les éléments de la liste transformés par la fonction `f`. Exemple :
```scala
scala> IntList(1, 2, 3).map(x => x + 1)
res3: tp.IntList = 2 :: 3 :: 4 :: nil
```

**Question 8** : Implémentez la méthode `filter(p: Int => Boolean): IntList`, retournant la liste des éléments satisfaisant le prédicat `p`.

**Question 9** : Implémentez `sum: Int` (retourne la somme des éléments de la liste) et `product: Int` (retourne le produit des éléments de la liste).

Remarquez que les implémentations des méthodes `sum` et `product` sont très similaires.

**Question 10** : Factorisez les parties communes de `sum` et `product` en faisant apparaître une méthode `fold(z: Int, op: (Int, Int) => Int): Int`, prenant en paramètre une valeur initiale `z` et une opération `op`, et retournant l’accumulation de l’application de l’opération à toutes les valeurs de la liste. La fonction `op` est appliquée la première fois sur le premier élément de la liste et la valeur initiale `z`, puis sur le deuxième élément de la liste et la valeur obtenue précédemment, puis sur le troisième élément de la liste et la valeur obtenue précédemment, et ainsi de suite. Il doit être ensuite possible d’implémenter `sum` et `product` en termes de `fold` :
```scala
def sum = fold(0, (x, acc) => x + acc)
def product = fold(1, (x, acc) => x * acc)
```

***

À ce stade du TP, s’il vous reste moins d’une demie heure avant la fin passez directement à la question 17. Sachez toutefois que vous ne serez pleinement ému par la beauté de la programmation fonctionnelle que lorsque vous aurez résolu les questions 13 et suivantes.

**Question 11** : Implémentez la méthode `forall(p: Int => Boolean): Boolean`, retournant `true` si tous les éléments de la liste satisfont le prédicat `p`.

***

Notez les similarités entre les méthodes `fold` et `forall` : toutes les deux parcourent la totalité de la liste pour produire une valeur (numérique dans le cas de `fold`, booléenne dans le cas de `forall`).

**Question 12** : Implémentez une méthode `foldBool(z: Boolean, op: (Int, Boolean) => Boolean): Boolean`, similaire à `fold` mais produisant une valeur booléenne à partir de la liste. Il doit être ensuite possible d’implémenter `forall` en termes de `foldBool` :
```scala
def forall(p: Int => Boolean) = foldBool(true, (x, r) => r && p(x))
```

***

Observez les signatures des méthodes `fold` et `foldBool` :
```scala
(Int,     (Int, Int)     => Int)     => Int
(Boolean, (Int, Boolean) => Boolean) => Boolean
```
`fold` produit un `Int` à partir d’une liste de nombres, `foldBool` produit un `Boolean`, imaginez maintenant que l’on souhaite écrire une fonction similaire pour produire une `String`, sa signature serait :
```scala
(String,  (Int, String)  => String)  => String
```
Dans ces trois cas, la signature suit le modèle suivant :
```scala
(A, (Int, A) => A) => A
```
Plutôt que d’écrire trois fonctions qui dupliquent la même intention (produire un résultat en fonction des éléments de la liste), il est préférable de rendre la méthode `fold` plus générale en s’abstrayant du type du résultat.

**Question 13** : Rendez la méthode `fold` polymorphe : `fold[A](z: A, op: (Int, A) => A): A`.

Cette fonction `fold` est très importante : en fait **toutes** les méthodes de `IntList` peuvent être implémentées en termes de `fold`, celle-ci pourrait donc être la **seule** méthode abstraite de la classe `IntList`.

**Question 14** : Quel est le résultat de l’évaluation de l’expression `IntList(1, 2, 3).fold(IntList.nil, IntList.cons)`?

Pour les curieux, `fold` effectue un [catamorphisme](http://en.wikipedia.org/wiki/Catamorphism) sur le type de données algébrique définissant une liste comme étant soit une liste vide soit un constructeur prenant un élément et une fin de liste. Les catamorphismes (donc les fonctions de type `fold`) sont très utilisés en programmation fonctionnelle pour travailler sur les types de données algébriques. Remarquez que le *pattern matching* sur les types de données algébriques effectue également un catamorphisme.

**Question 15** : Implémentez `forall` et `toString` en utilisant `fold`.

**Question 16** : Implémentez `map` en utilisant en `fold`.

Comme énoncé précédemment, vous pouvez vous amuser (si si) à remonter toutes les autres opérations dans `IntList`, en les implémentant en termes de `fold`. Cette dernière sera donc la seule opération abstraite de `IntList`.

# Traits

Vous disposez de la classe suivante décrivant un service donnant la consommation courante d’énergie d’une personne depuis le début de l’année.
```scala
class OPower {
  /**
   * @param person Name of the person
   * @return The amount of energy (in kWh) consumed by the person since the beginning of the year
   */
  def energy(person: String): Int = …
}
```

Cette classe réalise de nombreux calculs (ne faites *vraiment* pas attention à l’implémentation) pour parvenir à calculer la consommation d’énergie d’une personne par an, ce qui est trop coûteux en temps d’exécution.

**Question 17** : Écrivez un trait `Cache`, qui étend `OPower` et redéfinit la méthode `energy` pour utiliser un cache afin d’éviter de faire le calcul de la consommation d’une personne à chaque appel. Considérez que le cache expire au bout de 10 secondes (ce sera plus simple pour faire vos tests). Vous pouvez vous contenter de sauvegarder la valeur en mémoire en guise de cache. Vous avez le droit d’utiliser une variable pour cette question. Par contre interdiction d’utiliser la valeur `null`, jetez plutôt un œil à [`Option`](http://www.scala-lang.org/api/current/index.html#scala.Option).

Il doit être ensuite possible d’utiliser le trait `Cache` de la façon suivante :
```scala
scala> import tp._
import tp._

scala> val cachedOpower = new OPower with Cache
cachedOpower: tp.OPower with tp.Cache = $anon$1@3571ef12

scala> cachedOpower.energy("julien")
res0: Int = 4269

scala> cachedOpower.energy("julien")
res1: Int = 4269
```

**Question 18** : Écrivez un trait `RichOPower` qui étend `OPower` et définit la méthode `energyInJoules(person: String): Int` qui renvoie la consommation d’énergie en kilojoules plutôt qu’en kilowatt-heure (sachant que 1 kilowatt-heure vaut 3600 kilojoules).

**Question 19** : L’algorithme de linéarisation des classes est définit dans les [spécifications](http://www.scala-lang.org/docu/files/ScalaReference.pdf) du langage (définition 5.1.2 page 56). Quel est le résultat de la linéarisation de la classe `RichOPowerCached` définie comme suit :
```scala
class RichOPowerCached extends OPower with Cache with RichOPower
```