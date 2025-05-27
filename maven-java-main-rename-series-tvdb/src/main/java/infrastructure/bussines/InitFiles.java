package infrastructure.bussines;

import infrastructure.bussines.pojo.Serie;
import infrastructure.bussines.pojo.SerieRequest;

import java.util.List;

public interface InitFiles {
    SerieRequest getDataSearch();
    List<String> getListFiles();
    List<String> getPreviewRename(List<String> listOriginalNameFiles, List<Serie> listDataSearch);
    void renameFiles (List<String> listOriginalNameFiles, List<String> listPreviewNewNameFiles);

}
