



object Reversi {

  // Position type and utility functions

  type Position = (Int, Int)

  //   Given a Position value, determine whether or not it is a legal position on the board
  def isValidPos(p: Position): Boolean = ???

  // player type and utility functions
  abstract class Player(val name: String)

  case object PlayerWhite extends Player("white")

  case object PlayerBlack extends Player("black")


  // Given a Player value, return the opponent player
  def otherPlayer(p: Player): Player = ???

  // Piece type and utility functions

  case class Piece(position: Position, player: Player)

  // Given a Player value and a Piece value, does this piece belong to the player?
  def isPlayer(player: Player, piece: Piece): Boolean = ???

  // Given a Piece value, determine who the piece belongs to
  def playerOf(piece: Piece): Player = ???

  // Flip a piece over

  def flipPiece(piece: Piece): Piece = ???

  //  Board type and utility functions
  type Board = List[Piece]

  //    The initial configuration of the game board
  val initialBoard: Board =
    List(
      Piece((3, 4), PlayerWhite), Piece((4, 4), PlayerBlack),
      Piece((3, 3), PlayerBlack), Piece((4, 3), PlayerWhite)
    )

  // Given a Position value, is there a piece at that position?
  def isOccupied(position: Position, board: Board): Boolean = ???

  // Which piece is at a given position?
  // Return None in the case that there is no piece at the position
  // Otherwise return Some(the_piece)

  def pieceAt(position: Position, board: Board): Option[Piece] = ???


  //   -- Determine if a particular piece can be placed on a board.
  //   -- There are two conditions:
  //   -- (1) no two pieces can occupy the same space, and
  //   -- (2) at least one of the other player's pieces must be flipped by the placement of the new piece.

  def validMove(piece: Piece, board: Board): Boolean = ???

  // Determine which pieces would be flipped by the placement of a new piece

  def toFlip(piece: Piece, board: Board): List[Piece] = ???


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
  def makeMove(piece: Piece, board: Board): Board = ???


  //  -- Find all valid moves for a particular player

  def allMoves(player: Player, board: Board): List[Piece] = ???

  //    -- Count the number of pieces belonging to a player

  def score(player: Player, board: Board): Int = ???

  //     -- Decide whether or not the game is over. The game is over when neither player can make a validMove
  def isGameOver(board: Board): Boolean = ???

  //     -- Find out who wins the game
  //     -- Return None in the case of a draw.
  //     -- Otherwise return Some(the_Player)
  def winner(board: Board): Option[Player] = ???


}
