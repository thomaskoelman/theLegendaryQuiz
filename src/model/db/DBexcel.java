package model.db;

import model.domain.categories.Category;
import model.domain.questions.Question;

import java.util.ArrayList;

public class DBexcel implements Database {
    private ArrayList<Category> categories;
    private ArrayList<Question> questions;
}
