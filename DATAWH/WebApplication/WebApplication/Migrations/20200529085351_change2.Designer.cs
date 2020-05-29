﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using WebApplication.Database;

namespace WebApplication.Migrations
{
    [DbContext(typeof(MuseumContext))]
    [Migration("20200529085351_change2")]
    partial class change2
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "5.0.0-preview.4.20220.10")
                .HasAnnotation("Relational:MaxIdentifierLength", 128)
                .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

            modelBuilder.Entity("WebApplication.Datamodel.Administrator", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Email")
                        .IsRequired()
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("Password")
                        .IsRequired()
                        .HasColumnType("nvarchar(max)");

                    b.HasKey("Id");

                    b.ToTable("Administrators");
                });

            modelBuilder.Entity("WebApplication.Datamodel.Artwork", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<int>("ArtworkPosition")
                        .HasColumnType("int");

                    b.Property<string>("Author")
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("Comment")
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("Description")
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("Image")
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("Location")
                        .HasColumnType("nvarchar(max)");

                    b.Property<int>("MaxCo2")
                        .HasColumnType("int");

                    b.Property<int>("MaxHumidity")
                        .HasColumnType("int");

                    b.Property<int>("MaxLight")
                        .HasColumnType("int");

                    b.Property<int>("MaxTemperature")
                        .HasColumnType("int");

                    b.Property<int>("MinCo2")
                        .HasColumnType("int");

                    b.Property<int>("MinHumidity")
                        .HasColumnType("int");

                    b.Property<int>("MinLight")
                        .HasColumnType("int");

                    b.Property<int>("MinTemperature")
                        .HasColumnType("int");

                    b.Property<string>("Name")
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("RoomLocationCode")
                        .HasColumnType("nvarchar(450)");

                    b.Property<string>("Type")
                        .HasColumnType("nvarchar(max)");

                    b.HasKey("Id");

                    b.HasIndex("RoomLocationCode");

                    b.ToTable("Artworks");
                });

            modelBuilder.Entity("WebApplication.Datamodel.Museum", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Address")
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("Name")
                        .HasColumnType("nvarchar(max)");

                    b.HasKey("Id");

                    b.ToTable("Museum");
                });

            modelBuilder.Entity("WebApplication.Datamodel.Room", b =>
                {
                    b.Property<string>("LocationCode")
                        .HasColumnType("nvarchar(450)");

                    b.Property<int>("Co2")
                        .HasColumnType("int");

                    b.Property<int>("CurrentCapacity")
                        .HasColumnType("int");

                    b.Property<string>("Description")
                        .HasColumnType("nvarchar(max)");

                    b.Property<int>("Humidity")
                        .HasColumnType("int");

                    b.Property<int>("Light")
                        .HasColumnType("int");

                    b.Property<int>("Temperature")
                        .HasColumnType("int");

                    b.Property<int>("TotalCapacity")
                        .HasColumnType("int");

                    b.HasKey("LocationCode");

                    b.ToTable("Rooms");
                });

            modelBuilder.Entity("WebApplication.Datamodel.RoomMeasurement", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<decimal>("Co2")
                        .HasColumnType("decimal(8,4)");

                    b.Property<decimal>("Humidity")
                        .HasColumnType("decimal(8,4)");

                    b.Property<decimal>("Light")
                        .HasColumnType("decimal(8,4)");

                    b.Property<decimal>("Temperature")
                        .HasColumnType("decimal(8,4)");

                    b.Property<string>("roomNo")
                        .HasColumnType("nvarchar(max)");

                    b.HasKey("Id");

                    b.ToTable("RoomMeasurements");
                });

            modelBuilder.Entity("WebApplication.Datamodel.Visitor", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<int>("Age")
                        .HasColumnType("int");

                    b.Property<string>("FirstName")
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("Gender")
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("LastName")
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("Nationality")
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("ReasonToVisit")
                        .HasColumnType("nvarchar(max)");

                    b.Property<DateTime>("VisitingDate")
                        .HasColumnType("datetime2");

                    b.HasKey("Id");

                    b.ToTable("Visitors");
                });

            modelBuilder.Entity("WebApplication.Datamodel.Artwork", b =>
                {
                    b.HasOne("WebApplication.Datamodel.Room", null)
                        .WithMany("ArtworkList")
                        .HasForeignKey("RoomLocationCode");
                });
#pragma warning restore 612, 618
        }
    }
}
