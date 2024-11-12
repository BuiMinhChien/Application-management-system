--create database assignment_prj_bmc
--drop database assignment_prj_bmc
use assignment_prj_bmc



--tao cac bang
create table Branch (
branch_id varchar(8) primary key,
name nvarchar(max)
)
create table Major (
major_id varchar(8) primary key,
name nvarchar(max),
branch_id varchar(8)
foreign key (branch_id) references Branch(branch_id)
)
create table Student (
student_id varchar(8) primary key,
username varchar(100),
password varchar(100),
full_name nvarchar(100),
dob varchar(100),
gender nvarchar(10),
email varchar(100),
phone_number varchar(10),
address nvarchar(max),
major_id varchar(8),
registration_date varchar(100),
status nvarchar(20)
foreign key (major_id) references Major(major_id)
)
create table Student_affairs_officer (
staff_id varchar(8) primary key,
username varchar(100),
password varchar(100),
full_name nvarchar(100),
dob varchar(100),
gender nvarchar(10),
email varchar(100),
phone_number varchar(10),
address nvarchar(max),
department nvarchar(max),
registration_date varchar(100),
status nvarchar(20)
)
create table Application_category (
category_id varchar(8) primary key,
category_name nvarchar(200),
description nvarchar(max)
)
create table Application (
application_id varchar(8) primary key,
student_id varchar(8),
category_id varchar(8),
title nvarchar(200),
content nvarchar(max),
submission_date varchar(100),
status nvarchar(20)
foreign key (student_id) references Student(student_id),
foreign key (category_id) references Application_category(category_id)
)
create table Response (
response_id varchar(8) primary key,
application_id varchar(8),
staff_id varchar(8),
response_content nvarchar(max),
response_date varchar(100)
foreign key (staff_id) references Student_affairs_officer(staff_id),
foreign key (application_id) references Application(application_id)
)
create table Notification (
notification_id varchar(8) primary key,
notification_content nvarchar(max),
notification_date varchar(100),
status nvarchar(20),
student_id varchar(8),
application_id varchar(8),
response_id varchar(8)
foreign key (student_id) references Student(student_id),
foreign key (application_id) references Application(application_id),
foreign key (response_id) references Response(response_id)
)
create table Attachment (
attachment_id varchar(8) primary key,
file_path nvarchar(max),
upload_date varchar(100),
application_id varchar(8),
response_id varchar(8)
foreign key (application_id) references Application(application_id),
foreign key (response_id) references Response(response_id)
)
create table Avatar (
avatar_id varchar(8) primary key,
file_path nvarchar(max),
upload_date varchar(100),
student_id varchar(8),
staff_id varchar(8)
foreign key (student_id) references Student(student_id),
foreign key (staff_id) references Student_affairs_officer(staff_id)
)



--them cac gia tri vao cac bang
insert into Branch values
('BBA',N'Quản trị kinh doanh'),
('BEN',N'Ngôn ngữ Anh'),
('BIT',N'Công nghệ thông tin'),
('BJP',N'Ngôn ngữ Nhật'),
('BKR',N'Ngôn ngữ Hàn')
insert into Major values
('FIN',N'Tài chính','BBA'),
('HM',N'Quản trị khách sạn','BBA'),
('IB',N'Kinh doanh quốc tế','BBA'),
('MC',N'Truyền thông đa phương tiện','BBA'),
('MKT',N'Marketing','BBA'),
('CHN',N'Ngôn ngữ Anh-Trung','BEN'),
('ENG',N'Ngôn ngữ Anh-Anh','BEN'),
('AI',N'Trí tuệ nhân tạo','BIT'),
('GD',N'Thiết kế mỹ thuật số','BIT'),
('IA',N'An toàn thông tin','BIT'),
('IS',N'Hệ thống thông tin','BIT'),
('SE',N'Kỹ thuật phần mềm','BIT'),
('JP',N'Ngôn ngữ Nhật','BJP'),
('KR',N'Ngôn ngữ Hàn','BKR')
insert into Student values
('HE186719', 'chienbmhe186719', '123', N'Bùi Minh Chiến', '18/10/2004', N'Nam', 'chienbui@fpt.com', '0912345678', N'Vinh, Nghệ An', 'SE', '01/01/2024', N'Mở'),
('HE186623', 'hungcthe186623', '456', N'Cao Tiến Hùng', '07/07/2004', N'Nam', 'hungcao@fpt.com', '0987654321', N'Hải Hậu, Nam Định', 'SE', '15/02/2024', N'Mở'),
('HE183739', 'toandkhe183739', '789', N'Đặng Khánh Toàn', '27/12/2003', N'Nam', 'toandang@fpt.com', '0901234567', N'Thạch Thất, Hà Nội', 'GD', '10/03/2024', N'Mở');
insert into Student_affairs_officer values 
('SA216719', 'staff1', '123', N'Nguyễn Văn Toàn', '02/04/1997', N'Nam', 'toannguyen@fpt.com', '0932345678', N'Hương Sơn, Hà Tĩnh', N'Phòng quản lý đào tạo', '11/12/2023', N'Mở'),
('SA096623', 'staff2', '456', N'Trần Thị Bình', '09/03/1995', N'Nữ', 'binhtran@fpt.com', '0987644321', N'Vị Xuyên, Hà Giang', N'Phòng quản lý đào tạo', '19/04/2020', N'Mở'),
('SA153739', 'staff3', '789', N'Lê Văn Sỹ', '11/09/1989', N'Nam', 'syle@fpt.com', '0901264567', N'Cầu Giấy, Hà Nội', N'Phòng quản lý đào tạo', '20/11/2020', N'Mở');
insert into Application_category values
('appli001',N'Đề nghị phúc tra',N'Sinh viên gửi đơn đến hội đồng chấm thi để yêu cầu kiểm tra và chấm lại bài thi'),
('appli002',N'Các loại đơn khác',N'Các loại đơn cụ thể theo nhu cầu của sinh viên'),
('appli003',N'Đơn khiếu nại điểm danh',N'Sinh viên gửi đơn để phản ánh hoặc khiếu nại về việc điểm danh của mình trong các lớp học, buổi thực hành hoặc các hoạt động học tập khác'),
('appli004',N'Đăng ký thi cải thiện điểm',N'Sinh viên gửi đơn để yêu cầu được phép tham gia thi lại hoặc thi cải thiện điểm số của một môn học nào đó'),
('appli005',N'Đề nghị miễn điểm danh',N'Đơn được nộp cho nhà trường, nhằm xin phép miễn cho việc tính điểm danh của sinh viên trong một khoảng thời gian nhất định');
