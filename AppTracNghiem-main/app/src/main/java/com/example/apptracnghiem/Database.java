package com.example.apptracnghiem;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apptracnghiem.model.Category;
import com.example.apptracnghiem.model.Question;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Question.db";
    private static final int VERSION = 1;
    private SQLiteDatabase db;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null , VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db=sqLiteDatabase;

        //biến bảng chuyên mục
        final String CATEGORIES_TABLE = "CREATE TABLE " +
                Table.CategoriesTable.TABLE_NAME + "( " +
                Table.CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Table.CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";
        //biến bảng question
        final String QUESTIONS_TABLE = "CREATE TABLE " +
                Table.QuestionsTable.TABLE_NAME + " ( " +
                Table.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Table.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                Table.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                Table.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                Table.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                Table.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                Table.QuestionsTable.COLUMN_ANSWER + " INTEGER, " +
                Table.QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + Table.QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                Table.CategoriesTable.TABLE_NAME + "(" + Table.CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";
        //Tao bang
        db.execSQL(CATEGORIES_TABLE);
        db.execSQL(QUESTIONS_TABLE);

        //insert du lieu
        CreateCategories();
        CreateQuestions();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table.CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Table.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }
    //insert vào csdl
    private void insertCategories(@NonNull Category category){
        ContentValues values = new ContentValues();
        values.put(Table.CategoriesTable.COLUMN_NAME,category.getName());
        db.insert(Table.CategoriesTable.TABLE_NAME,null,values);
    }

    //Các giá trị insert vào bảng chuyên mục
    public void CreateCategories(){
        Category c1 = new Category("Ngữ Văn");
        insertCategories(c1);
        Category c2 = new Category("Lịch Sử");
        insertCategories(c2);
        Category c3 = new Category("Địa Lý");
        insertCategories(c3);
    }
    //thêm câu hỏi và đáp án
    private void insertQuestion(Question question){
        ContentValues values = new ContentValues();

        values.put(Table.QuestionsTable.COLUMN_QUESTION,question.getQuestion());
        values.put(Table.QuestionsTable.COLUMN_OPTION1,question.getOption1());
        values.put(Table.QuestionsTable.COLUMN_OPTION2,question.getOption2());
        values.put(Table.QuestionsTable.COLUMN_OPTION3,question.getOption3());
        values.put(Table.QuestionsTable.COLUMN_OPTION4,question.getOption4());
        values.put(Table.QuestionsTable.COLUMN_ANSWER,question.getAnswer());
        values.put(Table.QuestionsTable.COLUMN_CATEGORY_ID,question.getCategoryId());

        db.insert(Table.QuestionsTable.TABLE_NAME,null,values);
    }

    //tao du lieu bang cau hoi
    private void CreateQuestions(){
        //Dữ liệu bảng question

        Question q1 = new Question("Cuộc khai thác thuộc địa lần thứ hai (1919-1929) của thực dân Pháp ở Đông Dương được diễn ra trong hoàn cảnh nào?",
                "A. Nước Pháp đang chuyển sang giai đoạn chủ nghĩa đế quốc",
                "B. Nước Pháp bị thiệt hại nặng nề do cuộc chiến tranh xâm lược Việt Nam",
                "C. Nước Pháp bị thiệt hại nặng nề do cuộc chiến tranh thế giới thứ nhất (1914-1918)",
                "D. Tình hình kinh tế, chính trị ở Pháp ổn định", 3, 2);
        insertQuestion(q1);
        Question q2 = new Question("Thực dân Pháp tiến hành cuộc khai thác thuộc địa lần thứ hai ở Đông Dương (1919 - 1929) khi",
                "A. Hệ thống thuộc địa của chủ nghĩa đế quốc tan rã.",
                "B. Thế giới tư bản đang lâm vào khủng hoảng thừa.",
                "C. Cuộc chiến tranh thế giới thứ nhất kết thúc.",
                "D. Kinh tế các nước tư bản đang trên đà phát triển.", 3, 2);
        insertQuestion(q2);
        Question q3 = new Question("Ngành kinh tế nào được thực dân Pháp đầu tư nhiều nhất trong cuộc khai thác thuộc địa lần thứ hai (1919 – 1929) ở Đông Dương?",
                "A. Nông nghiệp", "B. Công nghiệp", "C. Tài chính- ngân hàng","D. Giao thông vận tải", 1, 2);
        insertQuestion(q3);

        Question q4 = new Question("Trong cuộc khai thác thuộc địa lần thứ hai ở Đông Dương (1919 - 1929), thực dân Pháp tập trung đầu tư vào",
                "A. Đồn điền cao su.", "B. Công nghiệp hóa chất.", "C. Công nghiệp luyện kim. ","D. Ngành chế tạo máy.", 1, 2);
        insertQuestion(q4);

        Question q5 = new Question("Trong cuộc khai thác thuộc địa lần thứ hai ở Đông Dương (1919 - 1929), thực dân Pháp chú trọng đầu tư vào",
                "A. Chế tạo máy.", "B. Công nghiệp luyện kim.", "C. Công nghiệp hóa chất.","D. Khai thác mỏ.", 4, 2);
        insertQuestion(q5);

        Question q6 = new Question("Trong cuộc khai thác thuộc địa lần thứ hai (1919), thực dân Pháp sử dụng biện pháp nào để tăng ngân sách Đông Dương?",
                "A. Mở rộng quy mô sản xuất", "B. Khuyến khích phát triển công nghiệp nhẹ", "C. Tăng thuế và cho vay lãi","D. Mở rộng trao đổi buôn bán", 3, 2);
        insertQuestion(q6);
        Question q7 = new Question("Giai cấp nào trong xã hội Việt Nam đầu thế kỉ XX có quan hệ gắn bó với giai cấp nông dân?",
                "A. Công nhân", "B. Địa chủ", "C. Tư sản","D. Tiểu tư sản", 1, 2);
        insertQuestion(q7);
        Question q8 = new Question("Sau chiến tranh thế giới thứ nhất, lực lượng xã hội có khả năng vươn lên nắm ngọn cờ lãnh đạo cách mạng Việt Nam là",
                "A. Đại địa chủ", "B. Trung địa chủ", "C. Tiểu địa chủ","D. Trung, tiểu địa chủ", 4, 2);
        insertQuestion(q8);
        Question q9 = new Question("Trung và tiểu địa chủ Việt Nam sau Chiến tranh thế giới thứ nhất là lực lượng",
                "A. có tinh thần chống Pháp và tay sai. ", "B. làm tay sai cho Pháp.","C. bóc lột nông dân và làm tay sai cho Pháp.","D. thỏa hiệp với Pháp.", 1, 2);
        insertQuestion(q9);
        Question q10 = new Question("Ai là tác giả của chương chương trình khai thác thuộc địa lần thứ hai của thực dân Pháp ở Đông Dương?",
                "A. Pô-đu-me", "B. Anbe-xarô", "C. Pôn-bô","D. Va-ren", 2, 2);
        insertQuestion(q10);
        Question q11 = new Question("Hà Nội là thủ đô nước nào?",
                "A. Mỹ", "B. Cà Màu", "C.Nam Cực","D.Việt Nam", 4, 3);
        insertQuestion(q11);
        Question q12 = new Question("Bộ phận từ mượn quan trọng nhất trong tiếng Việt là gì?",
                " A. Tiếng Hán", " B. Tiếng Pháp", "C. Tiếng Anh","D. Tiếng Nga", 1, 1);
        insertQuestion(q12);
        Question q13 = new Question("Đỉnh núi Pan-xi-păng có độ cao bao nhiêu mét?",
                "a. 3134 mét.", "b. 3143 mét.", "c. 3314 mét.","a. 1 mét 2", 2, 3);
        insertQuestion(q13);

        Question q14 = new Question("Xác định nội dung, ý nghĩa của vấn đề mà câu nói đề cập đến thuộc thao tác lập luận nào?",
                " A. Giải thích", " B. Phân tích", "C. Chứng minh","D. Bình luận", 1, 1);
        insertQuestion(q14);

        Question q15 = new Question("X. Xvai-gơ là nhà văn của nước nào?",
                " A. Pháp", " B. Áo", "C. Đức","D. Nga", 2, 1);
        insertQuestion(q15);

        Question q16 = new Question("Cửa khẩu nào sau đây nằm trên đường biên giới Việt Nam – Lào?",
                "a. Móng Cái.", "b. Hữu Nghị.", "c.Đồng Văn."," d. Lao Bảo.", 4, 3);
        insertQuestion(q16);

        Question q17 = new Question("Quần đảo của nước ta nằm ở ngoài khơi xa trên biển Đông là:",
                "a. Hoàng Sa.", "b. Thổ Chu.", "c.Trường Sa."," d. Câu A + C đúng.", 4, 3);
        insertQuestion(q17);

    }
    //ham tra ve du lieu cac chu de
    @SuppressLint("Range")
    public List<Category> getDataCategories(){
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ Table.CategoriesTable.TABLE_NAME,null);
        if(c.moveToFirst()){
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(Table.CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(Table.CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            }
            while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }
    //lay du lieu cau hoi va dap an co id = idCategory theo chu de da chon
    @SuppressLint("Range")
    public ArrayList<Question> getQuestions(int categoryID){
        ArrayList<Question> questionArrayList = new ArrayList<>();

        db = getReadableDatabase();

        String selection = Table.QuestionsTable.COLUMN_CATEGORY_ID+" = ? " ;
        
        String[] selectionArgs = new String[]{String.valueOf(categoryID)};

        Cursor c = db.query(Table.QuestionsTable.TABLE_NAME,null,selection,selectionArgs,null,null,null);

        if(c.moveToFirst()){
            do {
                Question question = new Question();

                question.setId(c.getInt(c.getColumnIndex(Table.QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(Table.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(Table.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(Table.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(Table.QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(Table.QuestionsTable.COLUMN_OPTION4)));
                question.setAnswer(c.getInt(c.getColumnIndex(Table.QuestionsTable.COLUMN_ANSWER)));
                question.setCategoryId(c.getInt(c.getColumnIndex(Table.QuestionsTable.COLUMN_CATEGORY_ID)));

                questionArrayList.add(question);
            }
            while (c.moveToNext());
        }
        c.close();
        return questionArrayList;
    }
}
