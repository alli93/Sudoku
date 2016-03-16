
public class State {
	public Grid board;
	
	public class Grid {
		int[][] grid; // grid[row][column]
		
		public Grid(int[][] grid){
			for(int i = 0; i < grid.length; i++) {
				for(int j = 0; j < grid.length; j++) {
					this.grid[i][j] = grid[i][j];
				}
			}
		}

		public int[] getRow(Position p) {
			if(p.getRow() >= grid.length) {
				throw new IndexOutOfBoundsException();
			}
			int[] row = new int[grid.length];
			for(int i = 0; i < grid.length; i++) {
				row[i] = grid[p.getRow()][i];
			}
			return row;
		}
		
		public int[] getColumn(Position p) {
			if(p.getColumn() >= grid.length) {
				throw new IndexOutOfBoundsException();
			}
			int[] column = new int[grid.length];
			for(int i = 0; i < grid.length; i++) {
				column[i] = grid[i][p.getColumn()];
			}
			return column;
		}
		
		public int[] getSquare(Position p) {
			if(p.getColumn() >= grid.length || p.getRow() >= grid.length) {
				throw new IndexOutOfBoundsException();
			}
			int[] square = new int[grid.length];
			int squareSize = (int) Math.sqrt((double) grid.length);
			int index = 0;
			for(int i = (p.getRow() - (p.getRow() % squareSize)); i % squareSize != 0; i++) {
				for(int j = (p.getColumn() - (p.getColumn() % squareSize)); j % squareSize != 0; j++) {
					square[index] = grid[i][j];
					index++;
				}
			}
			return square;
		}
	}
	public State() {
		
	}
}
