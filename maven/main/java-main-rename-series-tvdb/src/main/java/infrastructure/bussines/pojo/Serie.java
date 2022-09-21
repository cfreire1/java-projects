package infrastructure.bussines.pojo;
// anno de primera emision T1 -- nombre serie -- Nombre para identificar  el archivo -- serie(S)/ova(O) -- numero de capitulos totales -- numero de temporada

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Serie {
    private long age;
    private String nameBase;
    private String nameFindFile;
    private String serieOrOva;
    private long numberEpisodes;
    private long numberSeason;
    private boolean absoluteOrder;
    private long numEpiAbsoluteOrderStart;
}
