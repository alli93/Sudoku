public class Position
{
	public int row, column;

	public Position(int row, int column)
	{
		this.row = row;
		this.column = column;
	}

	public int getRow()
	{
		return row;
	}

	public int getColumn()
	{
		return column;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (this.column != other.column)
		{
			return false;
		}
		if (this.row != other.row)
		{
			return false;
		}
		return true;
	}
	
	@Override
	public String toString()
	{
		StringBuffer pos = new StringBuffer();
		pos.append(row + " " + column);
		return (pos.toString());
	}
}
