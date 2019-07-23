package hibernate2;

import hibernate2.entity.Author;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AuthorHelper helper = new AuthorHelper();

        List<Author> authorList = helper.getAuthorList();
        for (Author a : authorList){
            System.out.println(a.getId() + " " + a.getName());
        }
        
        Author author = helper.getAuthorById(1);
        System.out.println(author.getName());

        Author author1 = new Author();
        author1.setName("Brown");
        helper.addAuthor(author1);
    }
}
