import Reversi._

object GameTree {

  // Generic tree datatype
case class Tree[T](root: T, tree: List[Tree[T]])

def getRoot[T](tree: Tree[T]): T = tree.root

  // Function to build trees recursively.  f takes a tree element, and returns all
  // the child elements.
  def repTree[T](f: T => List[T])( root: T, depth: Int): Tree[T] = {
    if (depth == 0)
      Tree(root, List())
    else
      Tree(root, f(root).map(x => repTree(f)(x, depth - 1)))
  }



//  -- Labels for the game tree.  We need to know the current board, and which was
//  -- the last piece to be played.

  case class Game(piece: Piece, board: Board)

  def getBoard(game: Game): Board = game.board


//  -- An adaptor function for allMoves.  It is slightly complicated by the
//  -- possibility that a player may be forced to skip a turn (if the player cannot
//  -- make any valid moves)

  def gameMoves(game: Game): List[Game] = {
    val moves = allMoves(otherPlayer(game.piece.player), game.board)
    val movesPass = allMoves(game.piece.player, game.board)
    (if (moves.isEmpty) movesPass else moves).map(piece => Game(piece, makeMove(piece, game.board)))
  }


 // -- Construct a game tree

  def gameTree(root: Game, depth: Int): Tree[Game] = {
    repTree(gameMoves)(root, depth)
  }

//  -- Helper function to find out who's turn it is for any node in the game tree


  def movePlayer(gameTree: Tree[Game]): Player = gameTree.root.piece.player


 // -- Estimate the value of a position for a player
 // -- This could be modified to account for strategic concerns, e.g. corners are
 // -- worth more than edges which are worth more than other positions.


  def estimate(player: Player, game: Game): Int = score(player, game.board)

  def estimate_improve(player: Player, game: Game): Int = {
    //首先，我们应该知道一个估值表的问题，在黑白棋中，不同位置都有不同位置的估值，虽然这样的估值表的用处并不是很大，但却在某些细节中表现出至关重要的作用。
    //黑白棋的棋盘默认是8*8的，总共64格。
    //从游戏规则我们可以看出来，角上的子很重要，因为这里不会被对方转换，角边上的点很危险，它给了对方直接进角的机会。
    //边上中间的四个点比较重要，只能从一个方向被翻转……等等。
    //根据这样的经验，我们大致可以得到以下的估值表：
    //这就是估值表矩阵
    val score_matrix =  Array(
      Array(170, 20, 90, 90, 90, 90, 20, 170),
      Array(20, 0, 85, 85, 85, 85, 0, 20),
      Array(90, 85, 81, 81, 81, 81, 85, 90),
      Array(90, 85, 81, 81, 81, 81, 85, 90),
      Array(90, 85, 81, 81, 81, 81, 85, 90),
      Array(90, 85, 81, 81, 81, 81, 85, 90),
      Array(20, 0, 85, 85, 85, 85, 0, 20),
      Array(170, 20, 90, 90, 90, 90, 20, 170),
    )

    var score:Int = 0
    game.board.foreach(i => {
      if (i.player == player) {
        score = score + score_matrix(i.position._1)(i.position._2)
      }
    })
    return score
  }


  // -- Maximise or minimise the value of a game tree, depending on whose move it is

  def minimax(player: Player)(node: Tree[Game]): Int = {
    if (movePlayer(node) == player) maximise(player, node)
    else
      minimise(player, node)
  }

  //  -- Maximise the value of a game tree, return the maximised value
 // -- When we maximise, it is the ai player's turn

  def maximise(player: Player, gameTree: Tree[Game]): Int = {
    if (gameTree.tree.isEmpty) estimate_improve(player, gameTree.root)
    else
      gameTree.tree.map(minimax(player)).max
  }

 // -- Minimise the value of a game tree, return the minimised value
 // -- When we minimise, it is the other player's turn (not the ai player)

  def minimise(player: Player, gameTree: Tree[Game]): Int = {
    if (gameTree.tree.isEmpty) estimate_improve(otherPlayer(player), gameTree.root)
    else
      gameTree.tree.map(minimax(player)).max
  }

 // -- Use maximise to find the best move


  def bestMove(player: Player, gameTree: Tree[Game]): Game = {
    gameTree.tree.maxBy(m => maximise(player, m) ).root
}

  // determine the best move for a player
  def aiMove(lookahead: Int, player: Player, game: Game):  Game  = {
        bestMove(player, gameTree(game, lookahead))
  }

}
