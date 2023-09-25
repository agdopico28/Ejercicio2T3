import java.io.DataInputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable

fun main(args: Array<String>) {
    try {
        val f = DataInputStream(FileInputStream("Rutes.dat"))
        val o = ObjectOutputStream(FileOutputStream("Rutes.obj"))
        val rutas: MutableList<Ruta> = mutableListOf()
        while (f.available() > 0) {
            val nom = f.readUTF()
            val denivell = f.readInt()
            val desnivellAcumulat = f.readInt()
            val punts = f.readInt()
            val llista: MutableList<PuntGeo> = mutableListOf()
            for (i in 1..punts) {
                val nunPunt = f.readUTF()
                val lat = f.readDouble()
                val long = f.readDouble()
                val punt = PuntGeo(nunPunt, Coordenades(lat, long))
                llista.add(punt)
            }
            val ruta: Ruta = Ruta(nom, denivell, desnivellAcumulat, llista)
            rutas.add(ruta)
        }
        for (ruta in rutas) {
            ruta.mostrarRuta()
            o.writeObject(ruta)
        }
        o.close()
        f.close()
    }
    catch (e: Exception){
        println("error")
    }
}





