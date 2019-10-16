import scala.util.control.Breaks._
object Reversi {

  // Position type and utility functions

  type Position = (Int, Int)

  //   Given a Position value, determine whether or not it is a legal position on the board
  def isValidPos(p: Position): Boolean = {
    if (p._1 >= 0 && p._1 < 8 && p._2 >=0 && p._2 < 8) {
      return true
    } else {
      return false
    }
  }

  // player type and utility functions
  abstract class Player(val name: String)

  case object PlayerWhite extends Player("white")

  case object PlayerBlack extends Player("black")


  // Given a Player value, return the opponent player
  def otherPlayer(p: Player): Player = {
    if (p == PlayerBlack) {
      return PlayerWhite
    } else {
      return  PlayerBlack
    }
  }

  // Piece type and utility functions

  case class Piece(position: Position, player: Player)

  // Given a Player value and a Piece value, does this piece belong to the player?
  def isPlayer(player: Player, piece: Piece): Boolean = {
    if (piece.player == player) {
      return true
    } else {
      return false
    }
  }

  // Given a Piece value, determine who the piece belongs to
  def playerOf(piece: Piece): Player = {
    return piece.player
  }

  // Flip a piece over

  def flipPiece(piece: Piece): Piece = {
    val piece.player = otherPlayer(piece.player)
    return piece
  }

  //  Board type and utility functions
  type Board = List[Piece]

  //    The initial configuration of the game board
  val initialBoard: Board =
    List(
      Piece((3, 4), PlayerWhite), Piece((4, 4), PlayerBlack),
      Piece((3, 3), PlayerBlack), Piece((4, 3), PlayerWhite)
    )

  // Given a Position value, is there a piece at that position?
  def isOccupied(position: Position, board: Board): Boolean = {
    board.foreach(i => {
      if(i.position == position) {
        return true
      }
    })
    return false
  }

  // Which piece is at a given position?
  // Return None in the case that there is no piece at the position
  // Otherwise return Some(the_piece)

  def pieceAt(position: Position, board: Board): Option[Piece] = {
    board.foreach(i => {
      if (i.position._1 == position._1 && i.position._2 == position._2) {
        return Some(i)
      }
    })
    return None
  }


  //   -- Determine if a particular piece can be placed on a board.
  //   -- There are two conditions:
  //   -- (1) no two pieces can occupy the same space, and
  //   -- (2) at least one of the other player's pieces must be flipped by the placement of the new piece.

  def validMove(piece: Piece, board: Board): Boolean = {
    if (!isOccupied(piece.position, board)) {
      var direction_index = 0
      var flag = true
      var index = 0
      var pos: Position = piece.position
      type direction = (Int, Int)
      val directions = new Array[direction](8)
      directions(0) = (0, -1)
      directions(1) = (0, 1)
      directions(2) = (1, 0)
      directions(3) = (-1, 0)
      directions(4) = (-1, 1)
      directions(5) = (-1, -1)
      directions(6) = (1, 1)
      directions(7) = (1, -1)
      //八个方向 以按下的点为中心，想八个方向搜索，如果到边界了还是同色则搜下一方向，如果同色之间有连续的异色，则将异色变成同色，就生成了一张新的棋谱
      while (direction_index < 8) {
        pos = piece.position
        while (isValidPos(pos) || flag) {
          pos = (pos._1 + directions(direction_index)._1 , pos._2 + directions(direction_index)._2)
//          val pos._1 = pos._1 + directions(direction_index)._1
//          val pos._2 = pos._2 + directions(direction_index)._2
          val pie : Option[Piece] = pieceAt(pos, board)
          if ((pie.isEmpty || pie.getOrElse(piece).player == piece.player) && index == 0) {
            flag = false
          } else if (pie.isEmpty && index > 0) {
            flag = false
          } else if (pie.getOrElse(piece).player == piece.player && index > 0) {
            return true
          }
          index = index + 1
        }
        direction_index = direction_index + 1
      }
      return false
    } else {
      return false
    }
  }

  // Determine which pieces would be flipped by the placement of a new piece

  def toFlip(piece: Piece, board: Board): List[Piece] = {
    var new_board : Board = Nil
    if (!isOccupied(piece.position, board)) {
      var direction_index = 0
      var flag = true
      var index = 0
      var pos: Position = piece.position
      type direction = (Int, Int)
      var directsHasSame = Array(false, false, false, false, false, false, false, false)
      val directions = new Array[direction](8)
      directions(0) = (0, 1)
      directions(1) = (0, -1)
      directions(2) = (1, 0)
      directions(3) = (-1, 0)
      directions(4) = (-1, 1)
      directions(5) = (-1, -1)
      directions(6) = (1, 1)
      directions(7) = (1, -1)
      //八个方向 以按下的点为中心，想八个方向搜索，如果到边界了还是同色则搜下一方向，如果同色之间有连续的异色，则将异色变成同色，就生成了一张新的棋谱
      while (direction_index < 8) {
        pos = piece.position
        while (isValidPos(pos) || flag) {
          pos = (pos._1 + directions(direction_index)._1 , pos._2 + directions(direction_index)._2)
//          var pos._1 = pos._1 + directions(direction_index)._1
//          var pos._2 = pos._2 + directions(direction_index)._2
          val pie : Option[Piece] = pieceAt(pos, board)
          if ((pie.isEmpty || pie.getOrElse(piece).player == piece.player) && index == 0) {
            flag = false
          } else if (pie.isEmpty && index > 0) {
            flag = false
          } else if (pie.getOrElse(piece).player == piece.player && index > 0) {
            directsHasSame(direction_index) = true
          }
          index = index + 1
        }
        direction_index = direction_index + 1
      }

      flag = true
      direction_index = 0
      while (direction_index < 8) {
        pos = piece.position
        if (directsHasSame(direction_index)) {
          while (flag) {
            pos = (pos._1 + directions(direction_index)._1 , pos._2 + directions(direction_index)._2)
            //          var pos._1 = pos._1 + directions(direction_index)._1
            //          var pos._2 = pos._2 + directions(direction_index)._2
            val pie : Option[Piece] = pieceAt(pos, board)
            if (pie.getOrElse(piece).player == otherPlayer(piece.player)) {
              var pie2 = pie.getOrElse(piece)
              new_board =  pie2 +: new_board
            } else {
              flag = false
            }
          }
        }
        flag = true
        direction_index = direction_index + 1
      }

    }
    return new_board
  }


  /*
   Auxillary function for toFlip.
   You don't have to use this function if you prefer to define toFlip some other way.
   Determine which pieces might get flipped along a particular line
   when a new piece is placed on the board.
   The first argument is a vector (pair of integers) that describes
   the direction of the line to check.
   The second argument is the hypothetical new piece.
   The return value is either the empty list,
   a list where all pieces belong to the same player,
   or a list where the last piece belongs to the player of the hypothetical piece.
   Only in the last case can any of the pieces be flipped.
  */

  def getLineDir(d: (Int, Int), piece: Piece, board: Board): List[Piece] = ???

  /*
 Auxillary function for toFlip.
 You don't have to use this function if you prefer to define toFlip some other way.
 Given the output from getLineDir, determine which, if any, of the pieces would be flipped.
*/
  def flippable(pieces: List[Piece]): List[Piece] = ???

  //   Place a new piece on the board.  Assumes that it constitutes a validMove
  def makeMove(piece: Piece, board: Board): Board = {
    var new_board = toFlip(piece, board)
//    println(new_board)
    var new_board1 : Board = Nil
    new_board.foreach(i => {
      board.foreach(j => {
        if (j.position._1 == i.position._1 && j.position._2 == i.position._2) {
          new_board1 = Piece(j.position, otherPlayer(i.player)) +: new_board1
        }else {
          new_board1 = j +: new_board1
        }
      })
    })
//    println(new_board1)
    return piece +: new_board1
  }


  //  -- Find all valid moves for a particular player

  def allMoves(player: Player, board: Board): List[Piece] = {
    var new_board : Board = Nil
    var x : Int = 0
    var y : Int = 0
    while (x < 8) {
      while (y < 8) {
        var piece : Piece = Piece((x, y), player)
        if (validMove(piece, board)) {
          new_board = piece +: new_board
        }
        y = y + 1
      }
      y = 0
      x = x + 1
    }
    return new_board
  }

  //    -- Count the number of pieces belonging to a player

  def score(player: Player, board: Board): Int = {
    var score:Int = 0
    board.foreach(i => {
      if (i.player == player) {
        score = score + 1
      }
    })
    return score
  }

  //     -- Decide whether or not the game is over. The game is over when neither player can make a validMove
  def isGameOver(board: Board): Boolean = {
    if (allMoves(PlayerWhite, board) == Nil &&  allMoves(PlayerBlack, board) == Nil) {
      return true
    }
    return false
  }

  //     -- Find out who wins the game
  //     -- Return None in the case of a draw.
  //     -- Otherwise return Some(the_Player)
  def winner(board: Board): Option[Player] = {
    if (isGameOver(board)) {
      if (score(PlayerWhite, board) > score(PlayerBlack, board)) {
        return Some(PlayerWhite)
      }else if (score(PlayerWhite, board) < score(PlayerBlack, board)) {
        return Some(PlayerBlack)
      } else {
        return None
      }
    }
    return None
  }


}
