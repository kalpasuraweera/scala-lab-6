import scala.io.StdIn.readLine

@main def main():Unit = getStudentInfo()

def getStudentInfo():Unit = {
    var studentInfo = getStudentInfoWithRetry()
    var percentage = (studentInfo._2.toDouble / studentInfo._3) * 100
    var grade = percentage match {
        case x if x >= 90 => 'A'
        case x if x >= 75 => 'B'
        case x if x >= 50 => 'C'
        case _ => 'D'
    }
    printStudentRecord(studentInfo ++ (percentage, grade))
}

def getStudentInfoWithRetry():Tuple3[String, Int, Int] = {
    println("Enter Student Name: ")
    var studentName = readLine()
    println("Enter Marks of Student: ")
    var studentMarks = readLine().toInt
    println("Enter Total Possible Marks: ")
    var totalPossibleMark = readLine().toInt
    
    var studentInfo = (studentName, studentMarks, totalPossibleMark)
    validateInput(studentInfo) match {
        case true => studentInfo
        case false => {
            println("Invalid Input. Please try again.")
            getStudentInfoWithRetry()
        }
    }
}

def validateInput(inputData: Tuple3[String, Int, Int]):Boolean = {
    inputData match {
        case (name, _, _) if name.isEmpty() => false
        case (_, marks, totalMarks) if marks > totalMarks => false
        case (_, marks, totalMarks) if marks < 0 => false
        case (_, _, totalMarks) if totalMarks <= 0 => false
        case _ => true
    }
}

def printStudentRecord(studentData: Tuple5[String, Int, Int, Double, Char]):Unit = {
    println(s"Student Name: ${studentData._1}")
    println(s"Student Marks: ${studentData._2}")
    println(s"Total Possible Marks: ${studentData._3}")
    println(s"Percentage: ${studentData._4}")
    println(s"Grade: ${studentData._5}")
}