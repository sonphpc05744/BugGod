create database Peach_Coffee

go
use Peach_Coffee

go
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[NhanVien]') AND type in (N'U'))
DROP TABLE [dbo].[NhanVien]


GO
create table NhanVien(
	MaNV nvarchar(10) primary key,
	TenNV nvarchar(50) not null,
	Email nvarchar(50),
	SDT nvarchar(10) not null,
	MatKhau nvarchar(50),
	NgayVaoLam datetime,
	GhiChu nvarchar(50),
	GioiTinh bit,
	TrangThai nvarchar(50),
	ChucVu int not null
)


go
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[LoaiSanPham]') AND type in (N'U'))
DROP TABLE [dbo].[LoaiSanPham]
go
create table LoaiSanPham(
	MaLSP nvarchar(10) primary key,
	TenLSP nvarchar(50),
	MoTa nvarchar(50)
)
go
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[KhuyenMai]') AND type in (N'U'))
DROP TABLE [dbo].[KhuyenMai]
go

create table KhuyenMai(
	MaKM nvarchar(10) PRIMARY KEY,
	TenKM nvarchar(50) NOT NULL,
	NgayBD datetime not null,
	NgayKT datetime not null,
	GiaKM float,
	GhiChu nvarchar(50)
)

go
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SanPham]') AND type in (N'U'))
DROP TABLE [dbo].[SanPham]

go
create table SanPham(
	MaSP nvarchar(10) primary key,
	TenSP nvarchar(50),
	SoLuong int,
	HinhAnh nvarchar(50),
	Gia float,
	GhiChu nvarchar(50),
	MaLSP nvarchar(10),
	MaKM nvarchar(10)
	foreign key (MaLSP) references LoaiSanPham(MaLSP),
	foreign key (MaKM) references KhuyenMai(MaKM)
)

go
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CaLamViec]') AND type in (N'U'))
DROP TABLE [dbo].[CaLamViec]

go
create table CaLamViec(
	MaCLV nvarchar(10) primary key,
	TenCLV nvarchar(50),
	NhanVienTrucCa nvarchar(10),
	Thu nvarchar(15),
	GhiChu nvarchar(50),
	foreign key (NhanVienTrucCa) references NhanVien(MaNV)
)

go
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[HoaDonChi]') AND type in (N'U'))
DROP TABLE [dbo].[HoaDonChi]

go
create table HoaDonChi(
	MaHD int identity(1,1) primary key,
	ThoiGianTao datetime,
	NguoiTao nvarchar(10),
	TongTienChi float,
	GhiChu nvarchar(50),
	foreign key (NguoiTao) references NhanVien(MaNV)
)

go
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[HoaDon]') AND type in (N'U'))
DROP TABLE [dbo].[HoaDon]

go
create table HoaDon(
	MaHD nvarchar(10) primary key,
	ThoiGianTao datetime,
	NguoiTao nvarchar(10),
	SanPham nvarchar(10),
	TongTien float,
	MaKM nvarchar(10),
	ChiPhiKhac float,
	HinhThucTT bit,
	GhiChu nvarchar(50),
	foreign key (NguoiTao) references NhanVien(MaNV),
	foreign key (SanPham) references SanPham(MaSP),
	foreign key (MaKM) references KhuyenMai(MaKM)
)

------------------------------------------------------
INSERT INTO [dbo].[NhanVien]
           ([MaNV]
           ,[TenNV]
           ,[Email]
           ,[SDT]
           ,[MatKhau]
           ,[NgayVaoLam]
           ,[GhiChu]
           ,[GioiTinh]
           ,[TrangThai]
           ,[ChucVu])
     VALUES
           ('phucnh',N'Nguyễn Hữu Phúc','phucnh@fpt.edu.vn','0989861544','123','11-11-2012',N'Chưa có',0,N'Đang hoạt động',1)
GO
-------------------

INSERT INTO [dbo].[LoaiSanPham]
           ([MaLSP]
           ,[TenLSP]
           ,[MoTa])
     VALUES
           ('MaLSP01',N'Thức uống',N'Không có')
GO
---------------------
INSERT INTO [dbo].[KhuyenMai]
           ([MaKM]
           ,[TenKM]
           ,[NgayBD]
           ,[NgayKT]
           ,[GiaKM]
           ,[GhiChu])
     VALUES
           ('KM01','Du an','2023-11-03','2023-12-15',200,N'Chưa có')
GO
-----------------
INSERT INTO [dbo].[SanPham]
           ([MaSP]
           ,[TenSP]
           ,[SoLuong]
           ,[HinhAnh]
           ,[Gia]
           ,[GhiChu]
           ,[MaLSP]
           ,[MaKM])
     VALUES
           
		   ('SP01',N'Trà lai',2,null,300000,N'Chưa có','MaLSP01','KM01')
GO
-----------------------
INSERT INTO [dbo].[CaLamViec]
           ([MaCLV]
           ,[TenCLV]
           ,[NhanVienTrucCa]
           
           ,[Thu]
           ,[GhiChu])
     VALUES
           
		   ('Ca1','Ca 1','phucnh','Monday',N'Chưa có')
GO
---------------------

INSERT INTO [dbo].[HoaDonChi]
           ([ThoiGianTao]
           ,[NguoiTao]
           ,[TongTienChi]
           ,[GhiChu])
     VALUES
           
		   ('2023-11-04','phucnh',150000,N'Chưa có')
GO
-----------------------
INSERT INTO [dbo].[HoaDon]
           ([MaHD]
           ,[ThoiGianTao]
           ,[NguoiTao]
           ,[SanPham]
           ,[TongTien]
           ,[MaKM]
           ,[ChiPhiKhac]
           ,[HinhThucTT]
           ,[GhiChu])
     VALUES
           
		   ('HD01','2023-11-05','phucnh','SP01',120000,'KM01',0,0,N'Chưa có')
GO

select * from NhanVien
select * from CaLamViec
select * from HoaDon
select * from HoaDonChi
select * from KhuyenMai
select * from LoaiSanPham
select * from SanPham