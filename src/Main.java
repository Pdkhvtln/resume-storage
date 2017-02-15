import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        //MainTestArrayStorage.main(args);
        //com.urise.webapp.MainArray.main(args);
        FileBrowse file = new FileBrowse();

        System.out.println(file.fileFly("/home/andrew/IdeaProjects/resume-storage"));

    }
}

//Сделать рекурсивный обход и вывод имени файлов в каталогах и подкаталогах (корневой каталог- ваш проект)
class FileBrowse {

    private File file;
    private File[] s;
    private StringBuilder res = new StringBuilder();

    public String fileFly(String path) {
        file = new File(path);
        s = file.listFiles();
        for (int j = 0; j < s.length; j++) {
            res.append(s[j].getPath()+'\n');
            if (s[j].isDirectory()) {
                FileBrowse dir = new FileBrowse();
                res.append(dir.fileFly(s[j].getPath()));
            }
        }
        return res.toString();
    }
}