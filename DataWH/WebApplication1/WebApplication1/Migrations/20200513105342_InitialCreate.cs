using Microsoft.EntityFrameworkCore.Migrations;

namespace WebApplication1.Migrations
{
    public partial class InitialCreate : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Museum",
                columns: table => new
                {
                    Id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Museum", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "RoomMeasurements",
                columns: table => new
                {
                    Id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Light = table.Column<int>(type: "INTEGER", nullable: false),
                    Temperature = table.Column<int>(type: "INTEGER", nullable: false),
                    Humidity = table.Column<int>(type: "INTEGER", nullable: false),
                    Co2 = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_RoomMeasurements", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Administrators",
                columns: table => new
                {
                    Id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Username = table.Column<string>(type: "TEXT", nullable: false),
                    Password = table.Column<string>(type: "TEXT", nullable: false),
                    MuseumId = table.Column<int>(type: "INTEGER", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Administrators", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Administrators_Museum_MuseumId",
                        column: x => x.MuseumId,
                        principalTable: "Museum",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateTable(
                name: "Rooms",
                columns: table => new
                {
                    LocationCode = table.Column<string>(type: "TEXT", nullable: false),
                    Description = table.Column<string>(type: "TEXT", nullable: true),
                    CurrentCapacity = table.Column<int>(type: "INTEGER", nullable: false),
                    TotalCapacity = table.Column<int>(type: "INTEGER", nullable: false),
                    Light = table.Column<int>(type: "INTEGER", nullable: false),
                    Temperature = table.Column<int>(type: "INTEGER", nullable: false),
                    Humidity = table.Column<int>(type: "INTEGER", nullable: false),
                    Co2 = table.Column<int>(type: "INTEGER", nullable: false),
                    LiveRoomMeasurementsId = table.Column<int>(type: "INTEGER", nullable: true),
                    MuseumId = table.Column<int>(type: "INTEGER", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Rooms", x => x.LocationCode);
                    table.ForeignKey(
                        name: "FK_Rooms_Museum_MuseumId",
                        column: x => x.MuseumId,
                        principalTable: "Museum",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_Rooms_RoomMeasurements_LiveRoomMeasurementsId",
                        column: x => x.LiveRoomMeasurementsId,
                        principalTable: "RoomMeasurements",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateTable(
                name: "Artworks",
                columns: table => new
                {
                    Id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Name = table.Column<string>(type: "TEXT", nullable: true),
                    Description = table.Column<string>(type: "TEXT", nullable: true),
                    Image = table.Column<string>(type: "TEXT", nullable: true),
                    Type = table.Column<string>(type: "TEXT", nullable: true),
                    Author = table.Column<string>(type: "TEXT", nullable: true),
                    Location = table.Column<string>(type: "TEXT", nullable: true),
                    MinLight = table.Column<int>(type: "INTEGER", nullable: false),
                    MaxLight = table.Column<int>(type: "INTEGER", nullable: false),
                    MinCo2 = table.Column<int>(type: "INTEGER", nullable: false),
                    MaxCo2 = table.Column<int>(type: "INTEGER", nullable: false),
                    MinTemperature = table.Column<int>(type: "INTEGER", nullable: false),
                    MaxTemperature = table.Column<int>(type: "INTEGER", nullable: false),
                    MinHumidity = table.Column<int>(type: "INTEGER", nullable: false),
                    MaxHumidity = table.Column<int>(type: "INTEGER", nullable: false),
                    RoomLocationCode = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Artworks", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Artworks_Rooms_RoomLocationCode",
                        column: x => x.RoomLocationCode,
                        principalTable: "Rooms",
                        principalColumn: "LocationCode",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Administrators_MuseumId",
                table: "Administrators",
                column: "MuseumId");

            migrationBuilder.CreateIndex(
                name: "IX_Artworks_RoomLocationCode",
                table: "Artworks",
                column: "RoomLocationCode");

            migrationBuilder.CreateIndex(
                name: "IX_Rooms_LiveRoomMeasurementsId",
                table: "Rooms",
                column: "LiveRoomMeasurementsId");

            migrationBuilder.CreateIndex(
                name: "IX_Rooms_MuseumId",
                table: "Rooms",
                column: "MuseumId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Administrators");

            migrationBuilder.DropTable(
                name: "Artworks");

            migrationBuilder.DropTable(
                name: "Rooms");

            migrationBuilder.DropTable(
                name: "Museum");

            migrationBuilder.DropTable(
                name: "RoomMeasurements");
        }
    }
}
