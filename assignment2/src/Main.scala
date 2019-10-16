


import assignment2 nment2.GameTree._
import assignment2 gnment2.Reversi._

object Main extends App {

  // set computer player
   def computerPlayer: Player = PlayerWhite

  // How many moves the computer player will look ahead
   def lookahead: Int = 3


  override def main(args: Array[String]) = {
    // Encodes the initial board state, and a hypothetical initial move.

    val initialMove: Game = Game(Piece((3, 4), PlayerWhite), Reversi.initialBoard)
    drawBoard(initialMove.board)
    println("Welcome to reversi (human vs. computer). q to quit")
    println("Human is playing black, computer is playing white")
    turn(initialMove, PlayerBlack)
  }

  // game loop
  def turn(lastMove: Game, player: Player): Unit = {
    val board = getBoard(lastMove)
   //getting score
    val scoreWhite = score(PlayerWhite, board)
    val scoreBlack = score(PlayerBlack, board)
    // game over
    if (isGameOver(board)) {
      drawBoard(board)
      println("Game over")
      //retrieve and display score
      println("Final score: Computer"  + " = " +scoreWhite +" Player ="+  scoreBlack )
      if(scoreBlack>scoreWhite)
          println("you won!")
          
      // player cannot make a move, so pass
    } else if (allMoves(player, board).isEmpty) {
      println(player.name + " Must pass")
      turn(lastMove, otherPlayer(player))
      // computer's turn
    } else if (player == computerPlayer) {
      
      //show the board 
      drawBoard(board)
      
       //display score
       println("current score: computer"  + " = " +scoreWhite +" player ="+  scoreBlack )
       
      val move = aiMove(lookahead, player, lastMove)
      println("Computer plays " + posToCoord(move.piece.position))
      turn(Game(move.piece, move.board), otherPlayer(player))

      // it is the human player's turn
    } else {
      drawBoard(board)
      val humanMove = getValidMove(player, board)
      turn(Game(humanMove, makeMove(humanMove, board)), otherPlayer(player))

    }
  }

// helper function to read and validate a move
  def getValidMove(player: Player, board: Board): Piece = {

    val p = scala.io.StdIn.readLine().trim
    if (p == "q" || p == "Q")
      System.exit(0)


    val x = coordToPos(p)
    x match {
      case Some(pos) => if (validMove(Piece(pos, player), board)) Piece(pos, player) else {
        println("Invalid move")
        getValidMove(player, board)
      }
      case None =>
        println("Invalid move")
        getValidMove(player, board)

    }
  }

  def coordToPos(posStr: String): Option[Position] = {
    if (posStr.length < 2)
      return None
    val col = posStr(0)
    val row = posStr(1)
    val x = "abcdefgh".indexOf(col)
    val y = "12345678".indexOf(row)
    if (x == -1 || y == -1)
      None
    else
      Some(x, y)
  }

  def posToCoord(p: Position): String = {
    val x = "abcdefgh" (p._1)
    val y = "12345678" (p._2)
    s"${x}${y}"
  }

// draw game board
  def drawBoard(board: Board): Unit = {

    println("  a b c d e f g h")

    for (y <- 7 to 0 by -1) {
      drawRow(y)
    }
    println("  a b c d e f g h")

    def drawRow(y: Int) = {
      print(y + 1)
      for (x <- 0 to 7) {
        val p = pieceAt((x, y), board)
        p match {
          case None => print(" +")
          case Some(c) => if (c.player == PlayerBlack) print(" B") else print(" W")
        }
      }
      print(" ")
      println(y + 1)
    }

  }

}
