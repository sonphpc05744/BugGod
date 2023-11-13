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
	TrangThai bit,
	ChucVu bit,
	Hinh nvarchar(50)
)



go
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[LoaiSanPham]') AND type in (N'U'))
DROP TABLE [dbo].[LoaiSanPham]
go
create table LoaiSanPham(
	MaLSP nvarchar(10) primary key,
	TenLSP nvarchar(50) not null,
	MoTa nvarchar(50),
	TrangThai nvarchar(50)
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
	GhiChu nvarchar(50),
	TrangThai bit
)

go
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SanPham]') AND type in (N'U'))
DROP TABLE [dbo].[SanPham]

go
create table SanPham(
	MaSP nvarchar(10) primary key,
	TenSP nvarchar(50) not null,
	SoLuong int,
	HinhAnh nvarchar(50),
	Gia float,
	GhiChu nvarchar(50),
	MaLSP nvarchar(10),
	MaKM nvarchar(10),
	TrangThai bit,
	foreign key (MaLSP) references LoaiSanPham(MaLSP),
	foreign key (MaKM) references KhuyenMai(MaKM)
)

go
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CaLamViec]') AND type in (N'U'))
DROP TABLE [dbo].[CaLamViec]

go
create table CaLamViec(
	MaCLV nvarchar(10) primary key,
	TenCLV nvarchar(50)  not null,
	NhanVienTrucCa nvarchar(10),
	Thu nvarchar(15),
	GhiChu nvarchar(50),
	TrangThai bit,
	foreign key (NhanVienTrucCa) references NhanVien(MaNV)
)
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[NhanVien]') AND type in (N'U'))
DROP TABLE [dbo].CaLam

go
create table CaLam(
	MaCaLam nvarchar(10) PRIMARY KEY,
	MaNV nvarchar(10), 
	TrangThai bit,
	MaCa nvarchar(10),
	GhiChu nvarchar(50),
	foreign key (MaNV) references NhanVien(MaNV),
	foreign key (MaCa) references CaLamViec(MaCLV)
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
	TrangThai nvarchar(50),
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
	TrangThai nvarchar(50),
	foreign key (NguoiTao) references NhanVien(MaNV),
	foreign key (SanPham) references SanPham(MaSP),
	foreign key (MaKM) references KhuyenMai(MaKM)
)
------------------------------------------
go


--------------
go
CREATE PROCEDURE ThongKeHoaDonChiTheoNgay
@NgayTinh datetime
AS
BEGIN
    SELECT *
    FROM HoaDonChi
    WHERE CONVERT(date, ThoiGianTao) = CONVERT(date, @NgayTinh)
END
 EXEC ThongKeHoaDonChiTheoNgay '2023-01-01'
 -----------------

 go
 CREATE PROCEDURE ThongKeHoaDonTheoNgay
@NgayTinh datetime
AS
BEGIN
    SELECT *
    FROM HoaDon
    WHERE CONVERT(date, ThoiGianTao) = CONVERT(date, @NgayTinh)
END
EXEC ThongKeHoaDonTheoNgay '2023-01-05'
----------------------

go
CREATE PROCEDURE ThongKeHoaDonChiTheoThang
@ThangTinh date
AS
BEGIN
    SELECT *
    FROM HoaDonChi
    WHERE FORMAT(ThoiGianTao, 'yyyy-MM') = FORMAT(@ThangTinh, 'yyyy-MM')
END
EXEC ThongKeHoaDonChiTheoThang '2023-01-01'
------------------
go
CREATE PROCEDURE ThongKeHoaDonTheoThang
@ThangTinh date
AS
BEGIN
    SELECT *
    FROM HoaDon
    WHERE FORMAT(ThoiGianTao, 'yyyy-MM') = FORMAT(@ThangTinh, 'yyyy-MM')
END
EXEC ThongKeHoaDonTheoThang '2023-01-01'

-----------------------------
go
CREATE PROCEDURE ThongKeHoaDonChiTheoNam
@NamTinh int
AS
BEGIN
    SELECT *
    FROM HoaDonChi
    WHERE YEAR(ThoiGianTao) = @NamTinh
END
EXEC ThongKeHoaDonChiTheoNam 2023

-----------------------
go
CREATE PROCEDURE ThongKeHoaDonTheoNam
@NamTinh int
AS
BEGIN
    SELECT *
    FROM HoaDon
    WHERE YEAR(ThoiGianTao) = @NamTinh
END
EXEC ThongKeHoaDonTheoNam 2023