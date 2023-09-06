package com.example.abhaydayma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        var empList = ArrayList<EmployeeDetails>()

        if(intent.getStringExtra("flag").equals("1")){
            empList.add(EmployeeDetails(intent.getStringExtra("name").toString(), intent.getStringExtra("phone").toString(),    intent.getStringExtra("mail").toString(), intent.getStringExtra("report").toString(), intent.getStringExtra("image").toString()))
        }

        empList.add(EmployeeDetails("Abhay Dayma", "9878923421", "abhaydayma@gmail.com", "21123", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Femployee%2F&psig=AOvVaw017BWdwgVm5LsWK3sxKMQW&ust=1690964299193000&source=images&cd=vfe&opi=89978449&ved=0CA4QjRxqFwoTCJDFtfiCu4ADFQAAAAAdAAAAABAD"))
        empList.add(EmployeeDetails("Vivek Ramaswami", "9873218927", "vivek@gmail.com", "20123", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Femployee%2F&psig=AOvVaw017BWdwgVm5LsWK3sxKMQW&ust=1690964299193000&source=images&cd=vfe&opi=89978449&ved=0CA4QjRxqFwoTCJDFtfiCu4ADFQAAAAAdAAAAABAD"))
        empList.add(EmployeeDetails("Abhishek Jail", "8778923421", "abhishek@gmail.com", "21123", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Femployee%2F&psig=AOvVaw017BWdwgVm5LsWK3sxKMQW&ust=1690964299193000&source=images&cd=vfe&opi=89978449&ved=0CA4QjRxqFwoTCJDFtfiCu4ADFQAAAAAdAAAAABAD"))
        empList.add(EmployeeDetails("Jayant Kumar", "9878423421", "jayant@gmail.com", "21123", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Femployee%2F&psig=AOvVaw017BWdwgVm5LsWK3sxKMQW&ust=1690964299193000&source=images&cd=vfe&opi=89978449&ved=0CA4QjRxqFwoTCJDFtfiCu4ADFQAAAAAdAAAAABAD"))
        empList.add(EmployeeDetails("Bhuvan Soni", "7878923421", "bhuvan@gmail.com", "21123", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Femployee%2F&psig=AOvVaw017BWdwgVm5LsWK3sxKMQW&ust=1690964299193000&source=images&cd=vfe&opi=89978449&ved=0CA4QjRxqFwoTCJDFtfiCu4ADFQAAAAAdAAAAABAD"))
        empList.add(EmployeeDetails("Anuj Dayma", "9098923421", "anuj@gmail.com", "21123", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Femployee%2F&psig=AOvVaw017BWdwgVm5LsWK3sxKMQW&ust=1690964299193000&source=images&cd=vfe&opi=89978449&ved=0CA4QjRxqFwoTCJDFtfiCu4ADFQAAAAAdAAAAABAD"))
        empList.add(EmployeeDetails("Anurag Sharma", "9876523421", "anurag@gmail.com", "21123", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Femployee%2F&psig=AOvVaw017BWdwgVm5LsWK3sxKMQW&ust=1690964299193000&source=images&cd=vfe&opi=89978449&ved=0CA4QjRxqFwoTCJDFtfiCu4ADFQAAAAAdAAAAABAD"))
        empList.add(EmployeeDetails("Aman Jain", "8765923421", "aman@gmail.com", "21123", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Femployee%2F&psig=AOvVaw017BWdwgVm5LsWK3sxKMQW&ust=1690964299193000&source=images&cd=vfe&opi=89978449&ved=0CA4QjRxqFwoTCJDFtfiCu4ADFQAAAAAdAAAAABAD"))
        empList.add(EmployeeDetails("Suyash Sharma", "7654323421", "suyash@gmail.com", "21123", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Femployee%2F&psig=AOvVaw017BWdwgVm5LsWK3sxKMQW&ust=1690964299193000&source=images&cd=vfe&opi=89978449&ved=0CA4QjRxqFwoTCJDFtfiCu4ADFQAAAAAdAAAAABAD"))

        recyclerView.adapter = MyEmpAdapter(this@MainActivity, empList)
        recyclerView.layoutManager = LinearLayoutManager(this)


    }
}