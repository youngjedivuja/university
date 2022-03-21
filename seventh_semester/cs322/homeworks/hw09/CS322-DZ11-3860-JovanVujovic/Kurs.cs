public class Kurs : IComparable {
	public string ImeKursa { get; set; }
	public int FondCasova { get; set; }
	public int MaksimalnoPrijava { get; set; }

	public Kurs() {
	}

	public Kurs(string imeKursa, int fondCasova, int maksimalnoPrijava) {
		ImeKursa = imeKursa;
		FondCasova = fondCasova;
		MaksimalnoPrijava = maksimalnoPrijava;
	}

	public int CompareTo(object? obj) {
		if (obj is not Kurs kurs) return 0;
		return kurs.MaksimalnoPrijava.CompareTo(MaksimalnoPrijava);
	}

	public override string ToString() {
		return ImeKursa + ";" + FondCasova + ";" + MaksimalnoPrijava;
	}
}